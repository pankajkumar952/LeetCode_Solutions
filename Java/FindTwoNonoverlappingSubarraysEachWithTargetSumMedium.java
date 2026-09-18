
import java.util.*;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;

        // best[i] = minimum length of a valid subarray
        // completely contained in arr[0..i]
        int[] best = new int[n];

        Arrays.fill(best, Integer.MAX_VALUE);

        int left = 0;
        int sum = 0;
        int answer = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            // Shrink window if sum is too large
            while (left <= right && sum > target) {
                sum -= arr[left];
                left++;
            }

            // If current window has sum == target
            if (sum == target) {
                int length = right - left + 1;

                // There is a previous non-overlapping subarray
                if (left > 0 && best[left - 1] != Integer.MAX_VALUE) {
                    answer = Math.min(
                        answer,
                        length + best[left - 1]
                    );
                }

                // Store the shortest target-sum subarray
                // ending at or before 'right'
                best[right] = length;
            }

            // Carry forward the best answer from previous positions
            if (right > 0) {
                best[right] = Math.min(best[right], best[right - 1]);
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}
