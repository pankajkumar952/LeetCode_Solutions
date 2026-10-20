
class Solution {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }

        long power = n;

        if (power < 0) {
            power = -power;
            x = 1.0 / x;
        }

        double result = 1.0;

        while (power > 0) {
            if ((power & 1L) == 1L) {
                result *= x;
            }

            x *= x;
            power >>= 1;
        }

        return result;
    }
}
