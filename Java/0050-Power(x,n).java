class Solution {
    public double myPow(double x, int n) {

        long N = Math.abs((long) n);

        double ans = 1;

        for (long i = 0; i < N; i++) {
            ans *= x;
        }

        return n < 0 ? 1 / ans : ans;
    }
}
