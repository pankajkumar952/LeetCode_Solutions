class Solution {
    int m, n;

    private int dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 0) return 0;
        int fish = grid[r][c];
        grid[r][c] = 0;
        return fish + dfs(grid, r + 1, c) + dfs(grid, r - 1, c)
                    + dfs(grid, r, c + 1) + dfs(grid, r, c - 1);
    }

    public int findMaxFish(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] > 0)
                    ans = Math.max(ans, dfs(grid, i, j));
        return ans;
    }
}
