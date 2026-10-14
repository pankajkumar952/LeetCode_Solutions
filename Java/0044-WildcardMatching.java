class Solution {
    public boolean isMatch(String s, String p) {
        int i = 0, j = 0;
        int star = -1, match = 0;

        while (i < s.length()) {
 
            if (j < p.length() &&
                (p.charAt(j) == s.charAt(i) || p.charAt(j) == '?')) {
                i++;
                j++;
            }
            else if (j < p.length() && p.charAt(j) == '*') {
                star = j;
                match = i;
                j++;
            }
            
            else if (star != -1) {
                j = star + 1;
                match++;
                i = match;
            }
            // No match is possible
            else {
                return false;
            }
        }

        // Skip any remaining '*' characters
        while (j < p.length() && p.charAt(j) == '*') {
            j++;
        }

        return j == p.length();
    }
}
