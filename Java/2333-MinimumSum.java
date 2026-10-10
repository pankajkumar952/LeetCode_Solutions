
import java.util.*;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long[] diff = new long[n];
        long operations = (long) k1 + k2;
        long sum = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            sum += diff[i];
        }

        if (sum <= operations) {
            return 0;
        }

        PriorityQueue<Long> maxHeap =
            new PriorityQueue<>(Collections.reverseOrder());

        for (long d : diff) {
            maxHeap.offer(d);
        }

        while (operations > 0) {
            long largest = maxHeap.poll();

            if (largest == 0) {
                break;
            }

            maxHeap.offer(largest - 1);
            operations--;
        }

        long result = 0;

        while (!maxHeap.isEmpty()) {
            long d = maxHeap.poll();
            result += d * d;
        }

        return result;
    }
}
