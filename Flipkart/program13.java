class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int max =0;
        for (int row = 0; row < grid.length; row++){
            for (int col = 0; col < grid[row].length; col++){
                if (grid[row][col] == 1)
                    max = Math.max(checkArea(row, col, grid), max);
            }
        }
        return max;
    }
    public int checkArea(int row, int col, int[][] grid){
        int counter = 1;
        int rowLen = grid.length;
        if (row < 0 || row >= rowLen)
            return 0;
        int colLen = grid[row].length;
        if (col >= colLen || col < 0 || grid[row][col] == 0)
            return 0;        
        grid[row][col] = 0; 
        counter+=checkArea(row,col+1,grid);
        counter+=checkArea(row,col-1,grid);
        counter+=checkArea(row+1,col,grid);
        counter+=checkArea(row-1,col,grid);
        return counter;
    }
}