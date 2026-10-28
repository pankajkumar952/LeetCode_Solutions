class Solution {
    public int countAsterisks(String s) {
        int count = 0;
        boolean insidePair = false;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '|') {
                insidePair = !insidePair;
            } else if (ch == '*' && !insidePair) {
                count++;
            }
        }

        return count;
    }
}
