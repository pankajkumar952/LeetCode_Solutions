class Solution {
    public int numOfSubarrays(int[] arr) {
        long mod = 1000000007;
        long even = 1;
        long odd = 0;
        long ans = 0;
        int prefix = 0;

        for (int num : arr) {
            prefix = (prefix + num) % 2;

            if (prefix == 1) {
                ans = (ans + even) % mod;
                odd++;
            } else {
                ans = (ans + odd) % mod;
                even++;
            }
        }

        return (int) ans;
    }
}

