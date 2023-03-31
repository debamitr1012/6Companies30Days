class Solution {
public:
    int minimumCardPickup(vector<int>& cards) {
        int n = cards.size();

        int i=0;
        int j=1;
        int ans = INT_MAX;
        unordered_map<int,int>mp;
        for(int i=0;i<n;i++){
            if(mp.find(cards[i])!=mp.end()){
                ans=min(ans,i-mp[cards[i]]+1);
                mp[cards[i]]=i;
            }
            mp[cards[i]]=i;
        }
        if(ans==INT_MAX) return -1;
        return ans;
    }
};