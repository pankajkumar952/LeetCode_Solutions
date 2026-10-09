
class Solution {
    public int minInsertions(String s) {
        int open = 0;
        int insertions = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                open++;
            } else {
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    // Found a pair of closing brackets
                    i++;
                } else {
                    // Insert one ')' to complete the pair
                    insertions++;
                }

                if (open > 0) {
                    open--;
                } else {
                    // Insert '(' to match the closing pair
                    insertions++;
                }
            }
        }

        // Every remaining '(' needs two ')'
        insertions += open * 2;

        return insertions;
    }
}
