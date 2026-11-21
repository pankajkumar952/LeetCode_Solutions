import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] children = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        int[] newParent = parent.clone();
        int[] last = new int[26];
        Arrays.fill(last, -1);

        dfs(0, children, s, last, newParent);

        List<Integer>[] newChildren = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            newChildren[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            newChildren[newParent[i]].add(i);
        }

        int[] answer = new int[n];
        calculate(0, newChildren, answer);

        return answer;
    }

    private void dfs(int node, List<Integer>[] children, String s,
                     int[] last, int[] newParent) {

        int ch = s.charAt(node) - 'a';
        int previous = last[ch];

        if (previous != -1) {
            newParent[node] = previous;
        }

        last[ch] = node;

        for (int child : children[node]) {
            dfs(child, children, s, last, newParent);
        }

        last[ch] = previous;
    }

    private int calculate(int node, List<Integer>[] children, int[] answer) {
        int size = 1;

        for (int child : children[node]) {
            size += calculate(child, children, answer);
        }

        answer[node] = size;
        return size;
    }
}
