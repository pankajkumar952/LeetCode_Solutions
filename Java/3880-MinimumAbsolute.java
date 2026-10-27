class Solution {
    public int minAbsoluteDifference(int[] nums) {
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                for (int j = 0; j < nums.length; j++) {
                    if (nums[j] == 2) {
                        ans = Math.min(ans, Math.abs(i - j));
                    }
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
