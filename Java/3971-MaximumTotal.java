
class Solution {
    private static final long MOD = 1_000_000_007L;

    public int maxTotalValue(int[] value, int[] decay, int m) {
        long totalPositive = 0;
        int maxValue = 0;

        for (int v : value) {
            totalPositive += v;
            maxValue = Math.max(maxValue, v);
        }

        long picks = Math.min((long) m, totalPositive);

        // Binary search for the m-th largest positive gain.
        long low = 1, high = maxValue;

        while (low < high) {
            long mid = low + (high - low + 1) / 2;
            long count = 0;

            for (int i = 0; i < value.length; i++) {
                if (value[i] >= mid) {
                    count += (value[i] - mid) / decay[i] + 1;
                    if (count >= picks) {
                        break;
                    }
                }
            }

            if (count >= picks) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }

        long threshold = low;
        long countGreater = 0;
        long answer = 0;

        // Sum all gains strictly greater than the threshold.
        for (int i = 0; i < value.length; i++) {
            long v = value[i];
            long d = decay[i];

            if (v > threshold) {
                long k = (v - 1 - threshold) / d + 1;

                countGreater += k;

                long first = v;
                long last = v - (k - 1) * d;

                long a = k;
                long b = first + last;

                if (a % 2 == 0) {
                    a /= 2;
                } else {
                    b /= 2;
                }

                answer = (answer + (a % MOD) * (b % MOD)) % MOD;
            }
        }

        // Fill remaining selections with the threshold value.
        long remaining = picks - countGreater;
        answer = (answer + (remaining % MOD) * (threshold % MOD)) % MOD;

        return (int) answer;
    }
}

