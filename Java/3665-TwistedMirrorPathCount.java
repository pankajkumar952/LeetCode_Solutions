import java.util.Arrays;

class Solution {
    private static final int MOD = 1_000_000_007;

    private int m, n;
    private int[] memo;

    public int uniquePaths(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        memo = new int[m * n * 2];
        Arrays.fill(memo, -2);

        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 || dp[i][j] == 0) {
                    continue;
                }

                long ways = dp[i][j];

           
                int next = resolve(grid, i, j + 1, 0);

                if (next != -1) {
                    int r = next / n;
                    int c = next % n;

                    dp[r][c] = (dp[r][c] + ways) % MOD;
                }

                next = resolve(grid, i + 1, j, 1);

                if (next != -1) {
                    int r = next / n;
                    int c = next % n;

                    dp[r][c] = (dp[r][c] + ways) % MOD;
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }

    private int resolve(int[][] grid, int r, int c, int dir) {
        int[] path = new int[m + n + 5];
        int len = 0;
        int result = -1;

        while (r >= 0 && r < m && c >= 0 && c < n) {
            if (grid[r][c] == 0) {
                result = r * n + c;
                break;
            }

            int state = (r * n + c) * 2 + dir;

            if (memo[state] != -2) {
                result = memo[state];
                break;
            }

            path[len++] = state;

            if (dir == 0) {
            
                r++;
                dir = 1;
            } else {
             
                c++;
                dir = 0;
            }
        }

        for (int k = 0; k < len; k++) {
            memo[path[k]] = result;
        }

        return result;
    }
}
