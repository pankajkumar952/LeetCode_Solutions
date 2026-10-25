class Solution {
    private int rows, cols;
    private void dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= rows || c >= cols
                || grid[r][c] == 1) {
            return;
        }

        grid[r][c] = 1;

        dfs(grid, r + 1, c); 
        dfs(grid, r - 1, c); 
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1); 
    }

    public int closedIsland(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;

        for (int r = 0; r < rows; r++) {
            dfs(grid, r, 0);
            dfs(grid, r, cols - 1);
        }
        for (int c = 0; c < cols; c++) {
            dfs(grid, 0, c);
            dfs(grid, rows - 1, c);
        }

        int count = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) {
                    count++;
                    dfs(grid, r, c);
                }
            }
        }

        return count;
    }
}
