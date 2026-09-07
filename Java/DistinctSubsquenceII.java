class Solution {
    public int distinctSubseqII(String s) {
        final long MOD = 1_000_000_007L;

        int n = s.length();

        // dp[i] = number of distinct subsequences
        // including the empty subsequence
        long[] dp = new long[n + 1];

        dp[0] = 1;

        // Last position of each character
        int[] last = new int[26];

        for (int i = 1; i <= n; i++) {
            int c = s.charAt(i - 1) - 'a';

            // Every previous subsequence can:
            // 1. Not take current character
            // 2. Take current character
            dp[i] = (2 * dp[i - 1]) % MOD;

            // Remove duplicates created by previous occurrence
            if (last[c] != 0) {
                dp[i] = (dp[i] - dp[last[c] - 1] + MOD) % MOD;
            }

            // Store current position
            last[c] = i;
        }

        // Remove empty subsequence
        return (int) ((dp[n] - 1 + MOD) % MOD);
    }
}
