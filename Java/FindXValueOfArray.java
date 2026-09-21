class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];

        // dp[r] = number of subarrays ending at the previous
        // position whose product % k == r
        long[] dp = new long[k];

        for (int num : nums) {
            int val = num % k;
            long[] next = new long[k];

            // Start a new subarray with nums[i]
            next[val]++;

            // Extend every subarray ending at the previous index
            for (int r = 0; r < k; r++) {
                next[(r * val) % k] += dp[r];
            }

            // Add all subarrays ending at the current index
            for (int r = 0; r < k; r++) {
                ans[r] += next[r];
            }

            dp = next;
        }

        return ans;
    }
}
