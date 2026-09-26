import java.util.*;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {

            // Opening brackets
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }

            // Closing brackets
            else {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                if (ch == ')' && top != '(') {
                    return false;
                }

                if (ch == ']' && top != '[') {
                    return false;
                }

                if (ch == '}' && top != '{') {
                    return false;
                }
            }
        }

        // All opening brackets must be closed
        return stack.isEmpty();
    }
}
