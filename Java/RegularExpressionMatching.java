
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m + 1][n + 1];

        // Empty string matches empty pattern.
        dp[0][0] = true;

        // Empty string can match patterns like a*, a*b*, a*b*c*, ...
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                char pc = p.charAt(j - 1);
                char sc = s.charAt(i - 1);

                // Normal character or '.'
                if (pc == '.' || pc == sc) {
                    dp[i][j] = dp[i - 1][j - 1];
                }

                // '*'
                else if (pc == '*') {
                    // Zero occurrences of the previous character.
                    dp[i][j] = dp[i][j - 2];

                    char prev = p.charAt(j - 2);

                    // One or more occurrences.
                    if (prev == '.' || prev == sc) {
                        dp[i][j] |= dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }
}
