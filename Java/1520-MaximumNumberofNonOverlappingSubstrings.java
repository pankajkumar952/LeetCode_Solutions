
import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        // Find first and last occurrence of every character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Find the minimum valid interval for each character
        for (int c = 0; c < 26; c++) {
            if (last[c] == -1) {
                continue;
            }

            int left = first[c];
            int right = last[c];
            boolean valid = true;

            for (int i = left; i <= right; i++) {
                int x = s.charAt(i) - 'a';

                // This character occurs before our left boundary,
                // so this interval cannot be valid.
                if (first[x] < left) {
                    valid = false;
                    break;
                }

                // We must include all occurrences of this character.
                right = Math.max(right, last[x]);
            }

            if (valid) {
                intervals.add(new int[]{left, right});
            }
        }

        // Choose intervals with the earliest ending position.
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> result = new ArrayList<>();
        int previousEnd = -1;

        for (int[] interval : intervals) {
            int left = interval[0];
            int right = interval[1];

            if (left > previousEnd) {
                result.add(s.substring(left, right + 1));
                previousEnd = right;
            }
        }

        return result;
    }
}
