class Solution {
    public long splitArray(int[] nums) {
        int n = nums.length;
        long[] prefix = new long[n];
        long[] suffix = new long[n];

        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        suffix[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + nums[i];
        }

        boolean[] inc = new boolean[n];
        boolean[] dec = new boolean[n];

        inc[0] = true;
        for (int i = 1; i < n; i++) {
            inc[i] = inc[i - 1] && nums[i] > nums[i - 1];
        }

        dec[n - 1] = true;
        for (int i = n - 2; i >= 0; i--) {
            dec[i] = dec[i + 1] && nums[i] > nums[i + 1];
        }

        long ans = Long.MAX_VALUE;

        for (int i = 0; i < n - 1; i++) {
            if (inc[i] && dec[i + 1]) {
                ans = Math.min(ans, Math.abs(prefix[i] - suffix[i + 1]));
            }
        }

        return ans == Long.MAX_VALUE ? -1 : ans;
    }
}
