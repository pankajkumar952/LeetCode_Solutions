class Solution {
    public int countCommas(int n) {
        int count = 0;

        // 1,000 to 999,999 -> each number has 1 comma
        if (n >= 1000) {
            count += n - 999;
        }

        return count;
    }
}
