class Solution {
    public long goodTriplets(int[] nums1, int[] nums2) {
        long res = 0;
        int[] pos = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) pos[nums2[i]] = i;
        FenwickTree fenwickTree = new FenwickTree(nums1.length);
        for (int i = 0; i < nums1.length; i++){
            int idx = pos[nums1[i]];
            long left = fenwickTree.queryBIT(idx);
            long right = nums1.length - 1 - idx - (i - left);
            res += left * right;
            fenwickTree.updateBIT(idx + 1, 1);
        }
        return res;
    }
    class FenwickTree {
        private int[] tree;
        public FenwickTree(int n){
            tree = new int[n + 2];
        }
        private int lsb(int i){
            return i &(-i);
        }
        public int queryBIT(int i){
            int sum = 0;
            for (; i > 0; i -= lsb(i)) sum += tree[i];
            return sum;
        }
        public void updateBIT(int i, int val){
            for (; i < tree.length; i += lsb(i)) tree[i] += val;
        }
    }
}