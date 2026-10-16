
class Solution {
    private static final long MOD = 1_000_000_007L;

    private long[] sum;
    private long[] sq;
    private long[] lazy;
    private int n;

    public int sumCounts(int[] nums) {
        n = nums.length;
        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int prev = last[x];

            update(1, 0, n - 1, prev, i);

            last[x] = i + 1;
            ans = (ans + sq[1]) % MOD;
        }

        return (int) ans;
    }

    private void apply(int node, int l, int r, long delta) {
        long len = r - l + 1L;
        long oldSum = sum[node];

        sq[node] = (sq[node]
                + 2L * delta % MOD * oldSum % MOD
                + delta * delta % MOD * len % MOD) % MOD;

        sum[node] = (oldSum + delta * len) % MOD;
        lazy[node] = (lazy[node] + delta) % MOD;
    }

    private void push(int node, int l, int r) {
        if (lazy[node] == 0 || l == r) {
            return;
        }

        int mid = l + (r - l) / 2;
        long delta = lazy[node];

        apply(node * 2, l, mid, delta);
        apply(node * 2 + 1, mid + 1, r, delta);

        lazy[node] = 0;
    }

    private void update(int node, int l, int r,
                        int ql, int qr) {
        if (qr < l || r < ql) {
            return;
        }

        if (ql <= l && r <= qr) {
            apply(node, l, r, 1);
            return;
        }

        push(node, l, r);

        int mid = l + (r - l) / 2;

        update(node * 2, l, mid, ql, qr);
        update(node * 2 + 1, mid + 1, r, ql, qr);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }
}
