class Solution {
    public double myPow(double x, int n) {
        double ans = 1.0;

        // Use long to safely handle Integer.MIN_VALUE
        long N = n;
        boolean neg = N < 0;

        // Convert negative exponent to positive
        if(neg) N = -N;

        while(N > 0) {
            if(N % 2 == 1) {
                // Odd exponent: include one x in the answer
                ans = ans * x;
                N = N - 1;
            }
            else {
                // Even exponent: square x and halve the exponent
                x = x * x;
                N = N / 2;
            }
        }

        // Negative exponent means taking the reciprocal
        if(neg) {
            return 1 / ans;
        }

        return ans;
    }
}
