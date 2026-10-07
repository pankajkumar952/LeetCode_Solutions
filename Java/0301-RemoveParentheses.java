import java.util.*;

class Solution {

    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();

        // Find the minimum number of '(' and ')' to remove
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

        dfs(s, 0, leftRemove, rightRemove, 0, new StringBuilder(), ans);

        return ans;
    }

    private void dfs(String s,
                     int index,
                     int leftRemove,
                     int rightRemove,
                     int balance,
                     StringBuilder path,
                     List<String> ans) {

        // Invalid balance: more ')' than '('
        if (balance < 0) {
            return;
        }

        // Not enough characters left to remove
        if (s.length() - index < leftRemove + rightRemove) {
            return;
        }

        if (index == s.length()) {
            if (leftRemove == 0 &&
                rightRemove == 0 &&
                balance == 0) {

                ans.add(path.toString());
            }
            return;
        }

        char c = s.charAt(index);

        // Case 1: Current character is '('
        if (c == '(') {

            // Option A: Remove it
            if (leftRemove > 0) {
                // Avoid duplicate removals
                if (index == 0 || s.charAt(index - 1) != '(') {
                    dfs(s,
                        index + 1,
                        leftRemove - 1,
                        rightRemove,
                        balance,
                        path,
                        ans);
                }
            }

            // Option B: Keep it
            path.append(c);

            dfs(s,
                index + 1,
                leftRemove,
                rightRemove,
                balance + 1,
                path,
                ans);

            path.deleteCharAt(path.length() - 1);

        }

        // Case 2: Current character is ')'
        else if (c == ')') {

            // Option A: Remove it
            if (rightRemove > 0) {
                // Avoid duplicate removals
                if (index == 0 || s.charAt(index - 1) != ')') {
                    dfs(s,
                        index + 1,
                        leftRemove,
                        rightRemove - 1,
                        balance,
                        path,
                        ans);
                }
            }

            // Option B: Keep it
            if (balance > 0) {
                path.append(c);

                dfs(s,
                    index + 1,
                    leftRemove,
                    rightRemove,
                    balance - 1,
                    path,
                    ans);

                path.deleteCharAt(path.length() - 1);
            }

        }

        // Case 3: Letter
        else {
            path.append(c);

            dfs(s,
                index + 1,
                leftRemove,
                rightRemove,
                balance,
                path,
                ans);

            path.deleteCharAt(path.length() - 1);
        }
    }
}
