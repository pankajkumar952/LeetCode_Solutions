import java.util.*;

class Solution {
    public int scoreOfStudents(String s, int[] answers) {
        int n = (s.length() + 1) / 2;

        int[] nums = new int[n];
        char[] ops = new char[n - 1];

        for (int i = 0; i < n; i++) {
            nums[i] = s.charAt(2 * i) - '0';
            if (i < n - 1) {
                ops[i] = s.charAt(2 * i + 1);
            }
        }

        int[] dp = new int[n];

        dp[0] = nums[0];

        for (int i = 0; i < n - 1; i++) {
            if (ops[i] == '*') {
                dp[i + 1] = dp[i] * nums[i + 1];
            } else {
                dp[i + 1] = nums[i + 1];
            }

            if (ops[i] == '+') {
                for (int j = 0; j <= i; j++) {
                    dp[i + 1] += (j == 0 ? nums[j] : 0);
                }
            }
        }

        int correct = 0;
        int sum = 0;
        int product = nums[0];

        for (int i = 0; i < n - 1; i++) {
            if (ops[i] == '*') {
                product *= nums[i + 1];
            } else {
                sum += product;
                product = nums[i + 1];
            }
        }

        correct = sum + product;

        Set<Integer>[][] possible = new HashSet[n][n];

        for (int i = 0; i < n; i++) {
            possible[i][i] = new HashSet<>();
            possible[i][i].add(nums[i]);
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                possible[i][j] = new HashSet<>();

                for (int k = i; k < j; k++) {
                    for (int a : possible[i][k]) {
                        for (int b : possible[k + 1][j]) {
                            int value = ops[k] == '+' ? a + b : a * b;

                            if (value <= 1000) {
                                possible[i][j].add(value);
                            }
                        }
                    }
                }
            }
        }

        Set<Integer> valid = possible[0][n - 1];

        int score = 0;

        for (int answer : answers) {
            if (answer == correct) {
                score += 5;
            } else if (valid.contains(answer)) {
                score += 2;
            }
        }

        return score;
    }
}

