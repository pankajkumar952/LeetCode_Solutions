import java.util.Arrays;

class Solution {
    public int minimizeSum(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;

        return Math.min(
            nums[n - 1] - nums[2],
            Math.min(
                nums[n - 2] - nums[1],
                nums[n - 3] - nums[0]
            )
        );
    }
}
