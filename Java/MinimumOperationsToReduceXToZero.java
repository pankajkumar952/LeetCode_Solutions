class Solution {
    public int minOperations(int[] nums, int x) {
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        int target = totalSum - x;

        // We need to remove everything.
        if (target == 0) {
            return nums.length;
        }

        // Impossible to keep a subarray with negative target sum.
        if (target < 0) {
            return -1;
        }

        int left = 0;
        int windowSum = 0;
        int maxLen = -1;

        for (int right = 0; right < nums.length; right++) {
            windowSum += nums[right];

            // Shrink the window if its sum is too large.
            while (left <= right && windowSum > target) {
                windowSum -= nums[left++];
            }

            // Found a subarray whose sum is exactly target.
            if (windowSum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        // Number of removed elements = total elements - kept elements.
        return maxLen == -1 ? -1 : nums.length - maxLen;
    }
}
