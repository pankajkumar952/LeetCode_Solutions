class Solution {
    static class Node {
        int prod;
        int[] cnt;

        Node(int k) {
            cnt = new int[k];
        }
    }

    int k;

    Node merge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;

        Node res = new Node(k);

        // Product of the whole segment
        res.prod = (int) ((long) left.prod * right.prod % k);

        // Prefixes entirely inside left
        for (int r = 0; r < k; r++) {
            res.cnt[r] += left.cnt[r];
        }

        // Prefixes = whole left + prefix of right
        for (int r = 0; r < k; r++) {
            int newR = (int) ((long) left.prod * r % k);
            res.cnt[newR] += right.cnt[r];
        }

        return res;
    }

    Node makeNode(int value) {
        Node node = new Node(k);

        int r = value % k;
        node.prod = r;
        node.cnt[r] = 1; // The one-element prefix

        return node;
    }

    Node[] tree;

    void build(int idx, int l, int r, int[] nums) {
        if (l == r) {
            tree[idx] = makeNode(nums[l]);
            return;
        }

        int mid = (l + r) >>> 1;

        build(idx << 1, l, mid, nums);
        build(idx << 1 | 1, mid + 1, r, nums);

        tree[idx] = merge(tree[idx << 1], tree[idx << 1 | 1]);
    }

    void update(int idx, int l, int r, int pos, int value) {
        if (l == r) {
            tree[idx] = makeNode(value);
            return;
        }

        int mid = (l + r) >>> 1;

        if (pos <= mid) {
            update(idx << 1, l, mid, pos, value);
        } else {
            update(idx << 1 | 1, mid + 1, r, pos, value);
        }

        tree[idx] = merge(tree[idx << 1], tree[idx << 1 | 1]);
    }

    Node query(int idx, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[idx];
        }

        int mid = (l + r) >>> 1;

        if (qr <= mid) {
            return query(idx << 1, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(idx << 1 | 1, mid + 1, r, ql, qr);
        }

        Node left = query(idx << 1, l, mid, ql, qr);
        Node right = query(idx << 1 | 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;

        int n = nums.length;
        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int qi = 0; qi < queries.length; qi++) {
            int index = queries[qi][0];
            int value = queries[qi][1];
            int start = queries[qi][2];
            int x = queries[qi][3];

            // This update persists for all following queries.
            update(1, 0, n - 1, index, value);

            // We need all prefixes of nums[start..n-1].
            Node res = query(1, 0, n - 1, start, n - 1);

            ans[qi] = res.cnt[x];
        }

        return ans;
    }
}
