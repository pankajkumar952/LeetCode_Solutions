
class Solution {
    public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int[] count = new int[s.length()];

        for (char c : s.toCharArray()) {
            int n = sb.length();
            sb.append(c);

            count[n] = (n > 0 && sb.charAt(n - 1) == c)
                       ? count[n - 1] + 1 : 1;

            if (count[n] == k) {
                sb.delete(n - k + 1, n + 1);
            }
        }

        return sb.toString();
    }
}

