class Solution {
    private static final long MOD = 1_000_000_007L;

    public int numberOfSets(int n, int k) {
        int N = n + k - 1;
        int R = 2 * k;

        long[] dp = new long[R + 1];
        dp[0] = 1;

        // Calculate C(N, R)
        for (int i = 1; i <= N; i++) {
            for (int j = Math.min(i, R); j >= 1; j--) {
                dp[j] = (dp[j] + dp[j - 1]) % MOD;
            }
        }

        return (int) dp[R];
    }
}
