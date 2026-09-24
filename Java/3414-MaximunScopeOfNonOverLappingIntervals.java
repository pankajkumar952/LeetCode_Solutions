import java.util.*;

class Solution {

    static class State {
        long score;
        int[] indices;

        State(long score, int[] indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        // [left, right, weight, originalIndex]
        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        // Sort by starting position.
        Arrays.sort(a, (x, y) -> {
            if (x[0] != y[0]) {
                return Integer.compare(x[0], y[0]);
            }
            return Integer.compare(x[1], y[1]);
        });

        // All starting positions.
        int[] starts = new int[n];

        for (int i = 0; i < n; i++) {
            starts[i] = a[i][0];
        }

        // next[i] = first interval whose left > a[i].right
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            next[i] = upperBound(starts, a[i][1]);
        }

        /*
         * dp[i][k]:
         * Best result using intervals from i onward,
         * choosing at most k intervals.
         */
        State[][] dp = new State[n + 1][5];

        // Choosing 0 intervals.
        for (int i = 0; i <= n; i++) {
            dp[i][0] = new State(0, new int[0]);
        }

        // No intervals remaining.
        for (int k = 1; k <= 4; k++) {
            dp[n][k] = new State(0, new int[0]);
        }

        for (int i = n - 1; i >= 0; i--) {

            for (int k = 1; k <= 4; k++) {

                // Option 1: skip current interval.
                State skip = dp[i + 1][k];

                // Option 2: take current interval.
                State nextState = dp[next[i]][k - 1];

                int[] takeIndices =
                        addAndSort(nextState.indices, a[i][3]);

                State take = new State(
                        nextState.score + a[i][2],
                        takeIndices
                );

                dp[i][k] = better(take, skip);
            }
        }

        return dp[0][4].indices;
    }

    /*
     * Add an original index and keep the array sorted.
     * Maximum size is only 4.
     */
    private int[] addAndSort(int[] arr, int value) {
        int[] result = Arrays.copyOf(arr, arr.length + 1);

        result[arr.length] = value;

        Arrays.sort(result);

        return result;
    }

    /*
     * Choose the state with:
     * 1. Larger score
     * 2. If equal score, lexicographically smaller indices
     */
    private State better(State a, State b) {

        if (a.score != b.score) {
            return a.score > b.score ? a : b;
        }

        if (lexicographicallySmaller(a.indices, b.indices)) {
            return a;
        }

        return b;
    }

    private boolean lexicographicallySmaller(int[] a, int[] b) {

        int len = Math.min(a.length, b.length);

        for (int i = 0; i < len; i++) {

            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }

        // If one is a prefix of the other,
        // the shorter one is lexicographically smaller.
        return a.length < b.length;
    }

    /*
     * First index where arr[index] > target.
     *
     * We need left > right because intervals sharing
     * a boundary are considered overlapping.
     */
    private int upperBound(int[] arr, int target) {

        int lo = 0;
        int hi = arr.length;

        while (lo < hi) {

            int mid = lo + (hi - lo) / 2;

            if (arr[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo;
    }
}
