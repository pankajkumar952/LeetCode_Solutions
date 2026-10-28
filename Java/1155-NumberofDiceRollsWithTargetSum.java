class Solution {
    public int numRollsToTarget(int n, int k, int target) {
        int MOD = 1_000_000_007;

        int[][] dp = new int[n + 1][target + 1];
        dp[0][0] = 1;

        for (int dice = 1; dice <= n; dice++) {
            for (int sum = 1; sum <= target; sum++) {
                for (int face = 1; face <= k && face <= sum; face++) {
                    dp[dice][sum] =
                        (int) ((dp[dice][sum] +
                        (long) dp[dice - 1][sum - face]) % MOD);
                }
            }
        }

        return dp[n][target];
    }
}
