import java.util.*;

class Solution {
    public int longestValidParentheses(String s) {

        Stack<Integer> stack = new Stack<>();

        // Base index before the start of a valid substring
        stack.push(-1);

        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {

                // Store index of '('
                stack.push(i);

            } else {

                // Remove matching '('
                stack.pop();

                if (stack.isEmpty()) {

                    // Current ')' cannot be matched
                    // Make this the new base
                    stack.push(i);

                } else {

                    // Valid substring length
                    int length = i - stack.peek();

                    maxLength = Math.max(maxLength, length);
                }
            }
        }

        return maxLength;
    }
}
