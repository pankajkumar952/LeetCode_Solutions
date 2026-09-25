import java.util.*;

class Solution {

    private final String[] phone = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    public List<String> letterCombinations(String digits) {

        List<String> result = new ArrayList<>();

        if (digits == null || digits.length() == 0) {
            return result;
        }

        backtrack(digits, 0, new StringBuilder(), result);

        return result;
    }

    private void backtrack(String digits,
                           int index,
                           StringBuilder current,
                           List<String> result) {

        // Base case
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // Get letters for current digit
        int digit = digits.charAt(index) - '0';
        String letters = phone[digit];

        // Try every possible letter
        for (char ch : letters.toCharArray()) {

            current.append(ch);

            // Move to next digit
            backtrack(digits, index + 1, current, result);

            // Undo choice
            current.deleteCharAt(current.length() - 1);
        }
    }
}
