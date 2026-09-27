import java.util.*;

class Solution {
    public String reverseParentheses(String s) {
        Stack<StringBuilder> stack = new Stack<>();
        StringBuilder current = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // Save the string built so far
                stack.push(current);
                current = new StringBuilder();

            } else if (c == ')') {
                // Reverse the content inside parentheses
                current.reverse();

                // Restore the string before '('
                StringBuilder previous = stack.pop();
                previous.append(current);

                current = previous;

            } else {
                current.append(c);
            }
        }

        return current.toString();
    }
}
