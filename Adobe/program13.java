class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int[][] dp = new int[dungeon.length][dungeon[0].length];
        for(int[] arr:dp)
            Arrays.fill(arr,-1);
        return helper(dp,dungeon,0,0);
    }
    public int helper(int[][] dp, int[][] dungeon, int r, int c) {
        int m=dungeon.length, n=dungeon[0].length;
        if(r==m-1 && c==n-1)
            return dungeon[r][c]>=0?1:-dungeon[r][c]+1;
        if(r>=0 && r<m && c>=0 && c<n){
            if(dp[r][c]!=-1)
                return dp[r][c];
            else {
                int res = Math.min(helper(dp,dungeon,r+1,c), helper(dp,dungeon,r,c+1))-dungeon[r][c];
                return dp[r][c] = res<=0?1:res;
            }
        }
        return Integer.MAX_VALUE;
    }
}