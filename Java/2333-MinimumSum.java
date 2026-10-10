
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];

        long k = (long) k1 + k2;
        int maxDiff = 0;
        long sum = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
            sum += diff[i];
        }

        if (sum <= k) {
            return 0;
        }

        // Find the smallest level that can be reached
        // using at most k operations.
        int low = 0, high = maxDiff;

        while (low < high) {
            int mid = low + (high - low) / 2;
            long operations = 0;

            for (int d : diff) {
                if (d > mid) {
                    operations += d - mid;
                }
            }

            if (operations <= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        int level = low;
        long used = 0;

        for (int d : diff) {
            if (d > level) {
                used += d - level;
            }
        }

        long remaining = k - used;
        long result = 0;

        for (int d : diff) {
            int reduced = Math.min(d, level);

            if (reduced == level && remaining > 0) {
                reduced--;
                remaining--;
            }

            result += (long) reduced * reduced;
        }

        return result;
    }
}
