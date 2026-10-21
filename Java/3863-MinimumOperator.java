
class Solution {
    public int minOperations(String s) {
        int n = s.length();
        boolean sorted = true;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                sorted = false;
                break;
            }
        }

        if (sorted) {
            return 0;
        }

        if (n == 2) {
            return -1;
        }

        char first = s.charAt(0);
        char last = s.charAt(n - 1);

        boolean firstMin = true;
        boolean lastMax = true;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < first) {
                firstMin = false;
            }
            if (s.charAt(i) > last) {
                lastMax = false;
            }
        }

        if (firstMin || lastMax) {
            return 1;
        }

        if (first == last) {
            return 3;
        }

        return 2;
    }
}
