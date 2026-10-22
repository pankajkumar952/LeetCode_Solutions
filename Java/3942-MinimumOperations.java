
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        // Check rotations of the original array
        int k = nums[0];
        boolean valid = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != (i + k) % n) {
                valid = false;
                break;
            }
        }

        if (valid) {
            return (n - k) % n;
        }

        // Check rotations after reversing
        k = nums[0];
        valid = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != (k - i + n) % n) {
                valid = false;
                break;
            }
        }

        if (valid) {
            return (n - k) % n + 1;
        }

        return -1;
    }
}
