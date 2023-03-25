class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int low = 0, high = n * n - 1; //initialize the binary search space
        while (low <= high) { //Binary search loop
            int mid = low + (high - low) / 2; //Calculate the middle of the binary search space
            if (canReachDestination(grid, mid, n)) { //Check if it's possible to reach the destination in the given time
                high = mid - 1; //If possible, search in the lower half of the binary search space
            } else {
                low = mid + 1; //If not possible, search in the upper half of the binary search space
            }
        }
        return low; //return the minimum time required to reach the destination
    }
    private boolean canReachDestination(int[][] grid, int time, int n) {
        int[] x_dir = new int[]{1, -1, 0, 0}; //direction array to traverse neighboring cells
        int[] y_dir = new int[]{0, 0, -1, 1};
        boolean[][] visited = new boolean[n][n]; //Visited array to mark the visited cells
        return dfs(grid, visited, 0, 0, n, time, x_dir, y_dir); //start the dfs from (0,0)
    }
    private boolean dfs(int[][] grid, boolean[][] visited, int i, int j, int n, int time, int[] x_dir, int[] y_dir) {
        if (i < 0 || j < 0 || i >= n || j >= n || time < grid[i][j] || visited[i][j]) { //base condition
            return false;
        }
        if (i == n - 1 && j == n - 1) { //If reached the destination return true
            return true;
        }
        visited[i][j] = true; //Mark the current cell as visited
        for (int k = 0; k < 4; k++) { //Traverse neighboring cells
            if (dfs(grid, visited, i + x_dir[k], j + y_dir[k], n, time, x_dir, y_dir)) {
                return true;
            }
        }
        return false; //If not able to reach destination return false
    }
}