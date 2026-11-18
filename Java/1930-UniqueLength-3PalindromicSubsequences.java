class Solution {
    public int countPalindromicSubsequence(String s) {
        int count = 0;

        for (char c = 'a'; c <= 'z'; c++) {
            int first = s.indexOf(c);
            int last = s.lastIndexOf(c);

            if (first != -1 && last - first > 1) {
                boolean[] seen = new boolean[26];

                for (int i = first + 1; i < last; i++) {
                    seen[s.charAt(i) - 'a'] = true;
                }

                for (boolean b : seen) {
                    if (b) count++;
                }
            }
        }

        return count;
    }
}
