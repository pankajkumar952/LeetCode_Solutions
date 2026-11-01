
class Solution {
    public int longestPalindromicSubsequence(String s, int k) {
        int n = s.length();
        int[][][] dp = new int[n][n][k + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][i][j] = 1;
            }
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;

                int diff = Math.abs(s.charAt(i) - s.charAt(j));
                int cost = Math.min(diff, 26 - diff);

                for (int ops = 0; ops <= k; ops++) {
                    dp[i][j][ops] = Math.max(
                        dp[i + 1][j][ops],
                        dp[i][j - 1][ops]
                    );

                    if (cost <= ops) {
                        int inner = (len == 2) ? 0 : dp[i + 1][j - 1][ops - cost];
                        dp[i][j][ops] = Math.max(
                            dp[i][j][ops],
                            inner + 2
                        );
                    }
                }
            }
        }

        return dp[0][n - 1][k];
    }
}

