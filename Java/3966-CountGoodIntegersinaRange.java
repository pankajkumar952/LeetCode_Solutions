import java.util.Arrays;

class Solution {
    private String s;
    private int k;
    private long[][][] memo;

    public long goodIntegers(long l, long r, int k) {
        this.k = k;
        return countUpTo(r) - countUpTo(l - 1);
    }

    private long countUpTo(long x) {
        if (x < 0) return 0;

        s = Long.toString(x);
        int n = s.length();

        memo = new long[n][11][2];

        for (long[][] row : memo) {
            for (long[] arr : row) {
                Arrays.fill(arr, -1);
            }
        }

        return dfs(0, 10, 1, 0);
    }

    private long dfs(int pos, int prev, int tight, int started) {

        if (pos == s.length()) {
            return 1;
        }

        if (tight == 0 && memo[pos][prev][started] != -1) {
            return memo[pos][prev][started];
        }

        int limit = (tight == 1) ? s.charAt(pos) - '0' : 9;

        long ways = 0;

        for (int d = 0; d <= limit; d++) {

            int newTight = (tight == 1 && d == limit) ? 1 : 0;

            if (started == 0 && d == 0) {

                ways += dfs(pos + 1, 10, newTight, 0);

            } else {

                if (started == 1 && Math.abs(prev - d) > k) {
                    continue;
                }

                ways += dfs(pos + 1, d, newTight, 1);
            }
        }

        if (tight == 0) {
            memo[pos][prev][started] = ways;
        }

        return ways;
    }
}
