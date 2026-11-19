class Solution {
    public long maxScore(int[] nums, int x) {
        long even = nums[0] % 2 == 0 ? nums[0] : Long.MIN_VALUE;
        long odd = nums[0] % 2 != 0 ? nums[0] : Long.MIN_VALUE;

        for (int i = 1; i < nums.length; i++) {
            long val = nums[i];

            if (val % 2 == 0) {
                even = Math.max(even + val, odd - x + val);
            } else {
                odd = Math.max(odd + val, even - x + val);
            }
        }

        return Math.max(even, odd);
    }
}
