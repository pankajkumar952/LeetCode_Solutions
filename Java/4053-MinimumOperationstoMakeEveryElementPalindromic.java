
class Solution {
    public long minOperations(int[] nums) {
        long total = 0;

        for (int num : nums) {
            String s = String.valueOf(num);
            int len = s.length();
            int half = (len + 1) / 2;

            long prefix = Long.parseLong(s.substring(0, half));
            long scale = pow10(half - 1);
            long best = Long.MAX_VALUE;

            // Nearby prefixes
            for (long p = prefix - 1; p <= prefix + 1; p++) {
                if (p >= scale && p < scale * 10) {
                    best = Math.min(
                        best, cost(num, makePalindrome(p, len))
                    );
                }
            }

            // Palindromes with matching parity and leading digits
            for (int digit = 1; digit <= 9; digit++) {
                if ((digit & 1) != (num & 1)) continue;

                long smallestPrefix = digit * scale;
                long largestPrefix = smallestPrefix + scale - 1;

                best = Math.min(best,
                    cost(num, makePalindrome(smallestPrefix, len)));

                best = Math.min(best,
                    cost(num, makePalindrome(largestPrefix, len)));
            }

            // Largest valid palindrome with one fewer digit
            if (len > 1) {
                int prevLen = len - 1;
                int prevHalf = (prevLen + 1) / 2;
                long prevScale = pow10(prevHalf - 1);

                long prevPrefix;
                if ((num & 1) == 1) {
                    prevPrefix = 10 * prevScale - 1;
                } else {
                    prevPrefix = 8 * prevScale + prevScale - 1;
                }

                long candidate = makePalindrome(prevPrefix, prevLen);
                best = Math.min(best, cost(num, candidate));
            }

            // Smallest valid palindrome with one extra digit
            long nextPower = pow10(len);
            long digit = ((num & 1) == 1) ? 1 : 2;
            long nextCandidate = digit * nextPower + digit;

            best = Math.min(best, cost(num, nextCandidate));

            total += best;
        }

        return total;
    }

    private long cost(long num, long candidate) {
        if (candidate <= 0 || ((candidate & 1) != (num & 1))) {
            return Long.MAX_VALUE;
        }

        return Math.abs(candidate - num) / 2;
    }

    private long makePalindrome(long prefix, int len) {
        long result = prefix;
        long x = (len % 2 == 0) ? prefix : prefix / 10;

        while (x > 0) {
            result = result * 10 + x % 10;
            x /= 10;
        }

        return result;
    }

    private long pow10(int n) {
        long result = 1;
        while (n-- > 0) result *= 10;
        return result;
    }
}

