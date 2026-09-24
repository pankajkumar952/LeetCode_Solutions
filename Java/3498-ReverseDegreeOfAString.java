class Solution {
    public int reverseDegree(String s) {
        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            int position = i + 1;

            // 'a' -> 26, 'b' -> 25, ..., 'z' -> 1
            int reverseValue = 'z' - s.charAt(i) + 1;

            sum += position * reverseValue;
        }

        return sum;
    }
}
