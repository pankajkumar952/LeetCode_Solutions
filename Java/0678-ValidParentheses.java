class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                minOpen++;
                maxOpen++;
            } 
            else if (ch == ')') {
                minOpen--;
                maxOpen--;
            } 
            else { // '*'
                minOpen--;  // '*' acts as ')'
                maxOpen++;  // '*' acts as '('
            }

            
            if (maxOpen < 0) {
                return false;
            }

            minOpen = Math.max(minOpen, 0);
        }

        // All '(' must be matched
        return minOpen == 0;
    }
}
