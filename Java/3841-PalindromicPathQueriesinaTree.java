
import java.util.*;

class Solution {
    private int n, LOG;
    private List<Integer>[] adj;

    private int[] tin, tout, depth, parent;
    private int[][] up;
    private int[] mask, rootPrefix, bit;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        this.n = n;

        LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj[u].add(v);
            adj[v].add(u);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        parent = new int[n];
        up = new int[LOG][n];

        mask = new int[n];
        rootPrefix = new int[n];
        bit = new int[n + 2];

        for (int i = 0; i < n; i++) {
            mask[i] = 1 << (s.charAt(i) - 'a');
        }

        buildTree();

        // Initialize Fenwick Tree with root-to-node XOR masks.
        for (int u = 0; u < n; u++) {
            rangeXor(tin[u], tin[u], rootPrefix[u]);
        }

        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split("\\s+");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                int newMask = 1 << (parts[2].charAt(0) - 'a');

                int delta = mask[u] ^ newMask;

                if (delta != 0) {
                    // Every node in u's subtree has an updated
                    // root-to-node prefix XOR.
                    rangeXor(tin[u], tout[u], delta);
                    mask[u] = newMask;
                }

            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int l = lca(u, v);

                int pathMask =
                        prefix(u) ^ prefix(v) ^ mask[l];

                // A palindrome permutation allows at most
                // one character with an odd frequency.
                answer.add(
                    (pathMask & (pathMask - 1)) == 0
                );
            }
        }

        return answer;
    }

    private void buildTree() {
        int[] order = new int[n];
        int[] stack = new int[n];

        int top = 0;
        int count = 0;

        stack[top++] = 0;
        parent[0] = 0;

        // Iterative DFS preorder traversal.
        while (top > 0) {
            int u = stack[--top];

            order[count] = u;
            tin[u] = ++count;

            rootPrefix[u] =
                    (u == 0)
                    ? mask[u]
                    : rootPrefix[parent[u]] ^ mask[u];

            up[0][u] = parent[u];

            for (int v : adj[u]) {
                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        // Compute subtree end positions in reverse preorder.
        for (int u = 0; u < n; u++) {
            tout[u] = tin[u];
        }

        for (int i = n - 1; i > 0; i--) {
            int u = order[i];
            int p = parent[u];

            tout[p] = Math.max(tout[p], tout[u]);
        }

        // Binary lifting table for LCA.
        for (int k = 1; k < LOG; k++) {
            for (int u = 0; u < n; u++) {
                up[k][u] = up[k - 1][up[k - 1][u]];
            }
        }
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int k = 0; k < LOG; k++) {
            if (((diff >> k) & 1) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return parent[u];
    }

    private int prefix(int u) {
        return sum(tin[u]);
    }

    // XOR-update every Euler position in [l, r].
    private void rangeXor(int l, int r, int value) {
        add(l, value);
        add(r + 1, value);
    }

    private void add(int index, int value) {
        while (index < bit.length) {
            bit[index] ^= value;
            index += index & -index;
        }
    }

    private int sum(int index) {
        int result = 0;

        while (index > 0) {
            result ^= bit[index];
            index -= index & -index;
        }

        return result;
    }
}

