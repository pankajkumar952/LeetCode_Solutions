import java.util.*;

class Solution {

    public List<String> removeInvalidParentheses(String s) {

        List<String> ans = new ArrayList<>();

        // Find minimum number of removals
        int leftRemove = 0;
        int rightRemove = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                leftRemove++;

            } else if (c == ')') {

                if (leftRemove > 0) {
                    leftRemove--;
                } else {
                    rightRemove++;
                }
            }
        }

        Set<String> set = new HashSet<>();

        dfs(s, 0, leftRemove, rightRemove,
            0, new StringBuilder(), set);

        ans.addAll(set);

        return ans;
    }

    private void dfs(String s,
                     int index,
                     int leftRemove,
                     int rightRemove,
                     int balance,
                     StringBuilder path,
                     Set<String> set) {

        // More ')' than '('
        if (balance < 0) {
            return;
        }

        // Not enough characters remaining to remove
        if (s.length() - index < leftRemove + rightRemove) {
            return;
        }

        // End of string
        if (index == s.length()) {

            if (leftRemove == 0 &&
                rightRemove == 0 &&
                balance == 0) {

                set.add(path.toString());
            }

            return;
        }

        char c = s.charAt(index);

        // --------------------------------
        // CASE 1: '('
        // --------------------------------
        if (c == '(') {

            // Remove '('
            if (leftRemove > 0) {

                dfs(s,
                    index + 1,
                    leftRemove - 1,
                    rightRemove,
                    balance,
                    path,
                    set);
            }

            // Keep '('
            path.append('(');

            dfs(s,
                index + 1,
                leftRemove,
                rightRemove,
                balance + 1,
                path,
                set);

            path.deleteCharAt(path.length() - 1);
        }

        // --------------------------------
        // CASE 2: ')'
        // --------------------------------
        else if (c == ')') {

            // Remove ')'
            if (rightRemove > 0) {

                dfs(s,
                    index + 1,
                    leftRemove,
                    rightRemove - 1,
                    balance,
                    path,
                    set);
            }

            // Keep ')' only if matching '(' exists
            if (balance > 0) {

                path.append(')');

                dfs(s,
                    index + 1,
                    leftRemove,
                    rightRemove,
                    balance - 1,
                    path,
                    set);

                path.deleteCharAt(path.length() - 1);
            }
        }

        // --------------------------------
        // CASE 3: Letter
        // --------------------------------
        else {

            path.append(c);

            dfs(s,
                index + 1,
                leftRemove,
                rightRemove,
                balance,
                path,
                set);

            path.deleteCharAt(path.length() - 1);
        }
    }
}
