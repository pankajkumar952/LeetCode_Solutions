
class Solution {
    public long minimumSteps(String s) {
        long swaps = 0;
        long black = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                black++;
            } else {
                swaps += black;
            }
        }

        return swaps;
    }
}

