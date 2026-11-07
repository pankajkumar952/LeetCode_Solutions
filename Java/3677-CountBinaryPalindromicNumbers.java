class Solution {
    public int countBinaryPalindromes(long n) {
        if (n == 0) return 1; // only "0"

        int len = 64 - Long.numberOfLeadingZeros(n);
        long count = 1; // counts k = 0

        for (int L = 1; L < len; L++) {
            int h = (L + 1) / 2;
            count += 1L << (h - 1);
        }

        int h = (len + 1) / 2;
        long prefix = n >> (len - h);        
        count += prefix - (1L << (h - 1));   // palindromes whose prefix is smaller than n's prefix

        // palindrome built from n's own prefix: include it only if <= n
        if (build(prefix, len) <= n) count++;

        return (int) count;
    }

    // build a palindrome of total length L from its first half (h bits)
    private long build(long prefix, int L) {
        long x = prefix;
        long t = (L % 2 == 1) ? prefix >> 1 : prefix;
        while (t > 0) {
            x = (x << 1) | (t & 1);
            t >>= 1;
        }
        return x;
    }
}
