class Solution {
public:
   vector<int> smallestTrimmedNumbers(vector<string>& nums, vector<vector<int>>& queries) {
    int n = nums[0].size();
    vector<pair<string, int>> pairs;
    for (int i = 0; i < nums.size(); ++i)
        pairs.push_back({ nums[i], i });
    vector<int> res;
    for(auto &q : queries) {
        int k = q[0], trim = q[1];
        if (k == 0)
            res.push_back(0);
        else {
            nth_element(begin(pairs), begin(pairs) + k - 1, end(pairs), [&](const auto &a, const auto &b){
                int p1 = n - trim, p2 = n - trim;
                while (p1 < n - 1 && a.first[p1] == '0')
                    ++p1;
                while (p2 < n - 1 && b.first[p2] == '0')
                    ++p2;
                if (p1 != p2)
                    return p1 > p2;
                int cmp = a.first.compare(p1, string::npos, b.first, p2, string::npos);
                return cmp == 0 ? a.second < b.second : cmp < 0;
            });
            res.push_back(pairs[k - 1].second);
        }}
    return res;
}};