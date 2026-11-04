
import java.util.*;

class Solution {
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);

        int n = events.length;

        int[] starts = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = events[i][0];
        }

        int[][] dp = new int[n + 1][k + 1];

        for (int i = n - 1; i >= 0; i--) {
            int next = findNext(starts, events[i][1]);

            for (int j = 1; j <= k; j++) {
                int take = events[i][2] + dp[next][j - 1];
                int skip = dp[i + 1][j];

                dp[i][j] = Math.max(take, skip);
            }
        }

        return dp[0][k];
    }

    private int findNext(int[] starts, int end) {
        int left = 0;
        int right = starts.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (starts[mid] <= end) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}

