class Solution {
    int N;
    boolean[][] seen;
    public int swimInWater(int[][] G) {
        N = G.length;
        int l=0, r=2500;
        while(l < r){
            int mid = (l+r)/2;
            seen = new boolean[N][N];
            if(dfs(0, 0, mid, G))
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
    boolean dfs(int r, int c, int lim, int[][] G){
        if(r<0||r>=N||c<0||c>=N || seen[r][c] || G[r][c] > lim)
            return false;
        else if(r==N-1 && c==N-1)
            return true;
        seen[r][c] = true;
        return dfs(r+1, c, lim, G) || dfs(r-1, c, lim, G) || dfs(r, c+1, lim, G) || dfs(r, c-1, lim, G);
    }
}