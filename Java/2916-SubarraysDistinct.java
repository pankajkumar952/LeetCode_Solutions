
class Solution {
    private static final int MOD = 1_000_000_007;

    public int sumCounts(int[] nums) {
        int n = nums.length;

        long[] sum = new long[4 * n];
        long[] squareSum = new long[4 * n];
        long[] lazy = new long[4 * n];

        int[] last = new int[100001];
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            int previous = last[x];

            // Increment distinct counts for subarrays
            // ending at index i, with start in [previous, i].
            update(1, 0, n - 1, previous, i,
                   sum, squareSum, lazy);

            last[x] = i + 1;

            // Sum of squared distinct counts for subarrays
            // ending at index i.
            ans = (ans + squareSum[1]) % MOD;
        }

        return (int) ans;
    }

    private void update(
            int node, int left, int right,
            int ql, int qr,
            long[] sum, long[] squareSum, long[] lazy) {

        if (ql > right || qr < left) {
            return;
        }

        if (ql <= left && right <= qr) {
            apply(node, left, right, sum, squareSum, lazy);
            return;
        }

        push(node, left, right, sum, squareSum, lazy);

        int mid = left + (right - left) / 2;

        update(node * 2, left, mid, ql, qr,
               sum, squareSum, lazy);

        update(node * 2 + 1, mid + 1, right, ql, qr,
               sum, squareSum, lazy);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;

        squareSum[node] =
                (squareSum[node * 2] + squareSum[node * 2 + 1]) % MOD;
    }

    private void apply(
            int node, int left, int right,
            long[] sum, long[] squareSum, long[] lazy) {

        long len = right - left + 1L;
        long oldSum = sum[node];
        long delta = 1;

        // (x + 1)^2 = x^2 + 2x + 1
        squareSum[node] =
                (squareSum[node] + 2L * oldSum + len) % MOD;

        sum[node] = (oldSum + len) % MOD;

        lazy[node] = (lazy[node] + delta) % MOD;
    }

    private void push(
            int node, int left, int right,
            long[] sum, long[] squareSum, long[] lazy) {

        if (lazy[node] == 0 || left == right) {
            return;
        }

        int mid = left + (right - left) / 2;

        apply(node * 2, left, mid, sum, squareSum, lazy);
        apply(node * 2 + 1, mid + 1, right,
              sum, squareSum, lazy);

        lazy[node] = 0;
    }
}
