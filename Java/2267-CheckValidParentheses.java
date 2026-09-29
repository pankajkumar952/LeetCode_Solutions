class Solution {

    private int m, n;
    private char[][] grid;
    private Boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;

        // A valid parentheses string must have even length.
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // Maximum possible balance is m + n.
        memo = new Boolean[m][n][m + n + 1];

        return dfs(0, 0, 0);
    }

    private boolean dfs(int row, int col, int balance) {

        // Update balance based on current character
        if (grid[row][col] == '(') {
            balance++;
        } else {
            balance--;
        }

        // Balance can never become negative
        if (balance < 0) {
            return false;
        }

        // Number of remaining cells is not enough to close all '('
        int remaining = (m - 1 - row) + (n - 1 - col);

        if (balance > remaining) {
            return false;
        }

        // Reached bottom-right
        if (row == m - 1 && col == n - 1) {
            return balance == 0;
        }

        // Already calculated
        if (memo[row][col][balance] != null) {
            return memo[row][col][balance];
        }

        boolean result = false;

        // Move down
        if (row + 1 < m) {
            result = dfs(row + 1, col, balance);
        }

        // Move right
        if (!result && col + 1 < n) {
            result = dfs(row, col + 1, balance);
        }

        memo[row][col][balance] = result;

        return result;
    }
}
