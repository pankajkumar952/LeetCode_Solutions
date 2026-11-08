import java.util.*;
class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int[] P = new int[n + 1];
        int t0 = 0, t1 = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') { P[i + 1] = P[i] + 1; t1++; }
            else { P[i + 1] = P[i] - 1; t0++; }
        }
        List<Integer>[] pos = new ArrayList[2 * n + 1];
        for (int i = 0; i <= n; i++) {
            int v = P[i] + n;
            if (pos[v] == null) pos[v] = new ArrayList<>();
            pos[v].add(i);
        }
        int ans = 0;
        for (int j = 0; j <= n; j++) {
            ans = Math.max(ans, j - pos[P[j] + n].get(0));   
            ans = Math.max(ans, best(pos, P[j] - 2 + n, j, 2 * t0));

            // balance -2: swap an inside '0' with an outside '1'
            // needs ones_in <= t1 - 1, i.e. length <= 2*t1
            ans = Math.max(ans, best(pos, P[j] + 2 + n, j, 2 * t1));
        }
        return ans;
    }

    // longest length j - i with i in pos[v], i < j, and j - i <= maxLen
    private int best(List<Integer>[] pos, int v, int j, int maxLen) {
        if (v < 0 || v >= pos.length || pos[v] == null) return 0;
        List<Integer> list = pos[v];
        int lo = 0, hi = list.size();
        int minI = j - maxLen;
        while (lo < hi) {                      // first index >= minI
            int mid = (lo + hi) >>> 1;
            if (list.get(mid) >= minI) hi = mid;
            else lo = mid + 1;
        }
        if (lo == list.size()) return 0;
        int i = list.get(lo);
        return i < j ? j - i : 0;
    }
}
