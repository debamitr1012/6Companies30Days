class Solution {
    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for(int i=0;i<m;i++) {
            markWater(grid, i, 0);
            markWater(grid, i, n - 1);
        }
        for(int j=0;j<n;j++) {
            markWater(grid, 0, j);
            markWater(grid, m - 1, j);
        }
        int ans = 0;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j] == 0) {
                    ans++;
                    dfs(grid, i, j);
                }
            }
        }
        return ans;
    }
    static void markWater(int[][] grid, int i, int j) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 1)
            return;
        grid[i][j] = 1;
        markWater(grid, i - 1, j);
        markWater(grid, i, j - 1);
        markWater(grid, i + 1, j);
        markWater(grid, i, j + 1);
    }
    static void dfs(int[][] grid, int i, int j) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 1 || grid[i][j] == 2)
            return;
        grid[i][j] = 2;
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
    }
}