class Solution {
public:
    void merge(vector<int>& nums, int offset, int n1, int n2) {
        int n{n1 + n2};         // total number of elements
        static vector<int> buf{};   // here we collect merge result
        buf.resize(n);
        int i1{offset};         // index in the left half
        int end1{offset + n1};  // index after the last elem in the left part
        int i2{end1};           // index in the right half
        int end2{end1 + n2};    // index after the last elem in the right part
        int j{};                // index in buffer
        while (i1 < end1 && i2 < end2) {
            if (nums[i1] <= nums[i2]) 
                buf[j++] = nums[i1++];
            else
                buf[j++] = nums[i2++];
        }
        while (i1 < end1) 
            buf[j++] = nums[i1++];
        while (i2 < end2) 
            buf[j++] = nums[i2++];
        // Write back to nums.
        for (int i{}; i < n; i++) 
            nums[offset + i] = buf[i];
    }
    long long sort(vector<int>& nums, int offset, int n1, int n2, int diff) {
        // Define indexes and boundaries for inversions count and merge:
        int base1{offset};       // index of the first element in the left half
        int base2{offset + n1};  // index of the first element in the right half
        int end1{base2};         // index after the last element in the left half
        int end2{base2 + n2};    // index after the last element in the right half
        long long ans{};  // answer (number of index pairs)
        // Sort halfs
        if (n1 > 1) {
            // Elements in the left part are NOT increased by diff.
            ans += sort(nums, offset, n1/2, n1 - n1/2, diff);
        }
        if (n2 > 1) {
            // Elements in the right part are increased by diff if this is
            // the rightmost half (end2 == nums.size())
            ans += sort(nums, offset+n1, n2/2, n2 - n2/2, diff);
        } else if (end2 == nums.size()) {
            // If this is the rightmost part, and no sort() on it was called,
            // e.g. n2 == 0 or 1, then no index pairs there. Increase by diff.
            for (int i{base2}; i < end2; i++) {
                nums[i] += diff;
            }
        }
        // Count inversions: number of elements in the left half less or equal
        // to elements in the right half + diff.
        // Note: left and right halfs are already sorted.
        int i1{base1}, i2{base2};
        // Depending on whether this is the rightmost half, we either temporarily
        // increase by diff, or permanently change values.
        if (end2 == nums.size()) {
            // Assume rightmost half is increased by diff.
            while (i1 < end1 && i2 < end2) {
                if (nums[i1] <= nums[i2]) {
                    // Since nums[i2+k] >= nums[i2], then all (n2 - i2) elements are.
                    ans += (end2 - i2); 
                    i1++;  // move to the next element in the left half
                } else {
                    i2++;  // nums[i1] > nums[i2] + diff. Maybe next i2 will be better.
                }
                // If i1 == end1, then there is some i2 <= n2, such that 
                //  all nums[i2] >= nums[i1], for i1 up to n1-1.
                // If i2 == end2, then there were some elements on the left, which were too large.
            }
            // If this is not the last iteration (i.e. offset != 0, since we know, that
            // this part is the righmost already), then we increase elements in the left
            // half and merge.
            if (offset > 0) {
                // Since this is the rightmost part,
                // we also increase left half elements by diff before merge.
                for (i1 = base1; i1 < end1; i1++)
                    nums[i1] += diff;
                merge(nums, offset, n1, n2);
            }
        } else {
            // This is NOT the rightmost half. It will be used somewhere later
            // as the leftmsot part, so we can not permanently increase elements by diff.
            while (i1 < end1 && i2 < end2) {
                if (nums[i1] <= nums[i2] + diff) {  // add diff here, result not stored.
                    ans += (end2 - i2); 
                    i1++;
                } else {
                    i2++;
                }
            }
            merge(nums, offset, n1, n2);
        }
        return ans;
    }
    long long numberOfPairs(vector<int>& nums1, vector<int>& nums2, int diff) {
        // Task is equal to find number of pairs (i, j) such that: 
        //      (nums1[i] - nums2[i]) <= (nums1[j] - nums2[j]) + diff
        // To simplify, subtract nums2 from nums1.
        const int n = nums1.size();
        for (int i{}; i < n; i++)
            nums1[i] -= nums2[i];
        vector<int>& nums{nums1};
        // Now task looks like find pairs (i, j), i < j, such that:
        //      nums[i] <= nums[j] + diff.
        // Since i < j, when i is fixed we only need to compare nums[i] with values
        // of (nums[j] + diff), for j > i. So, we can move from right to left,
        // adding `diff` to all visited elements, sorting and comparing.
        return sort(nums, 0, n/2, n - n/2, diff);
    }
};