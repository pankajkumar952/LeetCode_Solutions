class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();

        // pal[i][j] = true if s[i..j] is a palindrome
        boolean[][] pal = new boolean[n][n];

        // Build palindrome table
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) &&
                    (j - i <= 2 || pal[i + 1][j - 1])) {
                    pal[i][j] = true;
                }
            }
        }

        // dp[i] = max number of valid palindromes
        // in the first i characters
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            // Don't use s[i - 1]
            dp[i] = dp[i - 1];

            // Try every palindrome ending at i - 1
            for (int j = 0; j < i; j++) {
                if (i - j >= k && pal[j][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n];
    }
}
