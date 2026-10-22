class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        // Check every possible left rotation
        for (int k = 0; k < n; k++) {
            boolean sorted = true;

            for (int i = 0; i < n; i++) {
                if (nums[(i + k) % n] != i) {
                    sorted = false;
                    break;
                }
            }

            if (sorted) {
                return k;
            }
        }

        // Check every possible rotation after reversal
        int[] reversed = new int[n];

        for (int i = 0; i < n; i++) {
            reversed[i] = nums[n - 1 - i];
        }

        for (int k = 0; k < n; k++) {
            boolean sorted = true;

            for (int i = 0; i < n; i++) {
                if (reversed[(i + k) % n] != i) {
                    sorted = false;
                    break;
                }
            }

            if (sorted) {
                return k + 1;
            }
        }

        return -1;
    }
}
