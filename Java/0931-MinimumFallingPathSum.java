class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;

        int[][] dp = new int[n][n];

        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {

                int up = dp[i - 1][j];

                int leftDiagonal = (j > 0)
                        ? dp[i - 1][j - 1]
                        : Integer.MAX_VALUE;

                int rightDiagonal = (j < n - 1)
                        ? dp[i - 1][j + 1]
                        : Integer.MAX_VALUE;

                dp[i][j] = matrix[i][j]
                        + Math.min(up,
                        Math.min(leftDiagonal, rightDiagonal));
            }
        }

        int answer = Integer.MAX_VALUE;

        for (int j = 0; j < n; j++) {
            answer = Math.min(answer, dp[n - 1][j]);
        }

        return answer;
    }
}

