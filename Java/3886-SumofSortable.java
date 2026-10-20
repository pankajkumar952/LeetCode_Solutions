
class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean valid = true;

            for (int start = 0; start < n; start += k) {
                int descents = 0;

                for (int i = start; i < start + k - 1; i++) {
                    if (nums[i] > nums[i + 1]) {
                        descents++;
                    }
                }

                if (descents > 1) {
                    valid = false;
                    break;
                }
            }

            if (!valid) {
                continue;
            }

            int prevMax = Integer.MIN_VALUE;

            for (int start = 0; start < n; start += k) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;

                for (int i = start; i < start + k; i++) {
                    min = Math.min(min, nums[i]);
                    max = Math.max(max, nums[i]);
                }

                if (prevMax > min) {
                    valid = false;
                    break;
                }

                prevMax = max;
            }

            if (valid) {
                ans += k;
            }
        }

        return ans;
    }
}

