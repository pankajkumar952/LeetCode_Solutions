
class Solution {
    public double myPow(double x, int n) {
        long power = n;

        if (power < 0) {
            power = -power;
            x = 1.0 / x;
        }

        double result = 1.0;

        while (power > 0) {
            if ((power & 1) == 1) {
                result *= x;
            }

            x *= x;
            power >>= 1;
        }

        return result;
    }
}
