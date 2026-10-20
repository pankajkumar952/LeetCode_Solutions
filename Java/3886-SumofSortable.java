
class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        int[] sorted = nums.clone();
        java.util.Arrays.sort(sorted);

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean valid = true;

            for (int start = 0; start < n; start += k) {
                int end = start + k;
                int pivot = -1;

                for (int i = start + 1; i < end; i++) {
                    if (nums[i] < nums[i - 1]) {
                        if (pivot != -1) {
                            valid = false;
                            break;
                        }
                        pivot = i;
                    }
                }

                if (!valid) {
                    break;
                }

                if (pivot != -1 &&
                    nums[end - 1] > nums[start]) {
                    valid = false;
                    break;
                }
            }

            if (!valid) {
                continue;
            }

            int[] result = nums.clone();

            for (int start = 0; start < n; start += k) {
                java.util.Arrays.sort(result, start, start + k);
            }

            for (int i = 1; i < n; i++) {
                if (result[i] < result[i - 1]) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                ans += k;
            }
        }

        return ans;
    }
}
