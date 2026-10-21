
class Solution {
    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];
        boolean[] diagonal1 = new boolean[2 * n - 1];
        boolean[] diagonal2 = new boolean[2 * n - 1];

        return backtrack(0, n, cols, diagonal1, diagonal2);
    }

    private int backtrack(int row, int n,
                          boolean[] cols,
                          boolean[] diagonal1,
                          boolean[] diagonal2) {

        if (row == n) {
            return 1;
        }

        int count = 0;

        for (int col = 0; col < n; col++) {
            int d1 = row - col + n - 1;
            int d2 = row + col;

            if (cols[col] || diagonal1[d1] || diagonal2[d2]) {
                continue;
            }

            cols[col] = true;
            diagonal1[d1] = true;
            diagonal2[d2] = true;

            count += backtrack(row + 1, n, cols, diagonal1, diagonal2);

            cols[col] = false;
            diagonal1[d1] = false;
            diagonal2[d2] = false;
        }

        return count;
    }
}

