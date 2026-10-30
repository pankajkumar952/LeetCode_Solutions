
class Solution {
    public int minLength(String s, int numOps) {

        int n = s.length();

        int left = 1;
        int right = n;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (canMake(s, numOps, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean canMake(String s, int numOps, int len) {

        int n = s.length();

        // Special case: longest substring must be 1
        if (len == 1) {

            int count0 = 0;
            int count1 = 0;

            for (int i = 0; i < n; i++) {

                char expected0 = (i % 2 == 0) ? '0' : '1';
                char expected1 = (i % 2 == 0) ? '1' : '0';

                if (s.charAt(i) != expected0) {
                    count0++;
                }

                if (s.charAt(i) != expected1) {
                    count1++;
                }
            }

            return Math.min(count0, count1) <= numOps;
        }

        // For len >= 2, count required flips
        int operations = 0;
        int count = 1;

        for (int i = 1; i < n; i++) {

            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                operations += count / (len + 1);
                count = 1;
            }
        }

        operations += count / (len + 1);

        return operations <= numOps;
    }
}

