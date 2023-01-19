static int MOD=1e9+7;
class Solution {
public:
    int peopleAwareOfSecret(int n, int delay, int forget) {
        vector<long> memo(n+1,1),prefixSum(n+1,0);
        memo[0]=0; 
        for(int i=1;i<=n;i++) {
            memo[i]=(memo[i]+prefixSum[max(0,i-delay)]-prefixSum[max(0,i-forget)]+MOD)%MOD;
            prefixSum[i]=(prefixSum[i-1]+memo[i])%MOD; 
        }
        return (memo[n]-memo[n-forget]+MOD)%MOD; 
    }
};