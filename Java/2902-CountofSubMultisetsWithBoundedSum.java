import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int countSubMultisets(List<Integer> nums, int l, int r) {
        int[] freq = new int[20001];
        int total = 0;

        for (int x : nums) {
            freq[x]++;
            total += x;
        }

        r = Math.min(r, total);

        int[] dp = new int[r + 1];
        dp[0] = 1;

        for (int x = 1; x <= 20000; x++) {
            if (freq[x] == 0) {
                continue;
            }

            int count = freq[x];
            int[] next = new int[r + 1];

            for (int rem = 0; rem < x && rem <= r; rem++) {
                long window = 0;

                for (int sum = rem; sum <= r; sum += x) {
                    window += dp[sum];

                    if (sum - (count + 1) * x >= 0) {
                        window -= dp[sum - (count + 1) * x];
                    }

                    window %= MOD;
                    if (window < 0) {
                        window += MOD;
                    }

                    next[sum] = (int) window;
                }
            }

            dp = next;
        }

        long ans = 0;

        for (int sum = l; sum <= r; sum++) {
            ans = (ans + dp[sum]) % MOD;
        }

        ans = ans * (freq[0] + 1L) % MOD;

        return (int) ans;
    }
}

