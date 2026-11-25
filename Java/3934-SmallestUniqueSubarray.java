
class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        int left = 1;
        int right = n;
        int answer = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (hasUnique(nums, mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    private boolean hasUnique(int[] nums, int len) {
        int n = nums.length;
        long base = 100003;
        long mod = 1000000007L;

        long power = 1;
        for (int i = 0; i < len; i++) {
            power = (power * base) % mod;
        }

        long hash = 0;

        for (int i = 0; i < len; i++) {
            hash = (hash * base + nums[i]) % mod;
        }

        java.util.HashSet<Long> seen = new java.util.HashSet<>();
        seen.add(hash);

        for (int i = len; i < n; i++) {
            hash = (hash * base + nums[i]) % mod;
            hash = (hash - (nums[i - len] * power) % mod + mod) % mod;

            if (!seen.add(hash)) {
                return false;
            }
        }

        return true;
    }
}
