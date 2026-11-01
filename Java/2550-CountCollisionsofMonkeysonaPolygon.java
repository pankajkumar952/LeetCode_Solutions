
class Solution {
    public int monkeyMove(int n) {
        final int MOD = 1_000_000_007;
        return (int) ((pow(2, n, MOD) - 2 + MOD) % MOD);
    }

    private long pow(long base, long exp, int mod) {
        long result = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = result * base % mod;
            }

            base = base * base % mod;
            exp >>= 1;
        }

        return result;
    }
}

