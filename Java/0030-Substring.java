import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> ans = new ArrayList<>();

        if (s == null || words == null || words.length == 0) {
            return ans;
        }

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        if (s.length() < totalLen) {
            return ans;
        }

        // Frequency of words that we actually need
        Map<String, Integer> target = new HashMap<>();

        for (String word : words) {
            target.put(word, target.getOrDefault(word, 0) + 1);
        }

        // We need to check wordLen different starting offsets
        for (int offset = 0; offset < wordLen; offset++) {

            int left = offset;
            int right = offset;

            // Words currently inside our window
            Map<String, Integer> window = new HashMap<>();

            int count = 0;

            while (right + wordLen <= s.length()) {

                String word = s.substring(right, right + wordLen);
                right += wordLen;

                // Word is not required
                if (!target.containsKey(word)) {
                    window.clear();
                    count = 0;
                    left = right;
                    continue;
                }

                // Add current word
                window.put(word, window.getOrDefault(word, 0) + 1);
                count++;

                // Too many occurrences of this word
                while (window.get(word) > target.get(word)) {

                    String leftWord = s.substring(left, left + wordLen);

                    window.put(leftWord, window.get(leftWord) - 1);

                    left += wordLen;
                    count--;
                }

                // Exactly wordCount words
                if (count == wordCount) {
                    ans.add(left);

                    // Move window forward to search for next answer
                    String leftWord = s.substring(left, left + wordLen);

                    window.put(leftWord, window.get(leftWord) - 1);

                    left += wordLen;
                    count--;
                }
            }
        }

        return ans;
    }
}
