class Solution {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int count = 0;
        int depth = 0;
    }

    static class SegmentTree {
        int[] tree;
        int n;

        SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * (n + 1)];
        }

        void update(int node, int l, int r, int pos, int val) {
            if (l == r) {
                tree[node] += val;
                return;
            }

            int mid = (l + r) / 2;

            if (pos <= mid) {
                update(node * 2, l, mid, pos, val);
            } else {
                update(node * 2 + 1, mid + 1, r, pos, val);
            }

            tree[node] = Math.max(
                tree[node * 2],
                tree[node * 2 + 1]
            );
        }

        void update(int pos, int val) {
            update(1, 1, n, pos, val);
        }

        int query() {
            return tree[1];
        }
    }

    public int[] longestCommonPrefix(String[] words, int k) {

        int n = words.length;
        int[] ans = new int[n];

        if (n - 1 < k) {
            return ans;
        }

        int maxLen = 0;

        // Build Trie
        TrieNode root = new TrieNode();

        // Store the Trie path for every word.
        TrieNode[][] paths = new TrieNode[n][];

        for (int i = 0; i < n; i++) {

            String word = words[i];
            maxLen = Math.max(maxLen, word.length());

            paths[i] = new TrieNode[word.length()];

            TrieNode curr = root;

            for (int j = 0; j < word.length(); j++) {

                int idx = word.charAt(j) - 'a';

                if (curr.children[idx] == null) {
                    curr.children[idx] = new TrieNode();
                    curr.children[idx].depth = curr.depth + 1;
                }

                curr = curr.children[idx];
                curr.count++;

                paths[i][j] = curr;
            }
        }

        // Count valid Trie nodes at each depth.
        int[] depthCount = new int[maxLen + 1];

        // Traverse Trie.
        java.util.ArrayDeque<TrieNode> queue =
            new java.util.ArrayDeque<>();

        queue.offer(root);

        while (!queue.isEmpty()) {

            TrieNode node = queue.poll();

            if (node.count >= k) {
                depthCount[node.depth]++;
            }

            for (TrieNode child : node.children) {
                if (child != null) {
                    queue.offer(child);
                }
            }
        }

        // Initialize Segment Tree.
        SegmentTree seg = new SegmentTree(maxLen);

        for (int d = 1; d <= maxLen; d++) {
            if (depthCount[d] > 0) {
                seg.update(d, 1);
            }
        }

        // Process removal of each word.
        for (int i = 0; i < n; i++) {

            // Temporarily remove affected Trie nodes.
            for (TrieNode node : paths[i]) {

                if (node.count == k) {

                    int d = node.depth;

                    depthCount[d]--;

                    if (depthCount[d] == 0) {
                        seg.update(d, -1);
                    }
                }
            }

            // Maximum valid prefix length.
            ans[i] = seg.query();

            // Restore Trie nodes.
            for (TrieNode node : paths[i]) {

                if (node.count == k) {

                    int d = node.depth;

                    depthCount[d]++;

                    if (depthCount[d] == 1) {
                        seg.update(d, 1);
                    }
                }
            }
        }

        return ans;
    }
}

