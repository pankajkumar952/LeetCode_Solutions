
import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];

        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }

        Arrays.sort(squares);

        long ans = 0;
        int half = n / 2;

        for (int i = 0; i < n; i++) {
            if (i < half) {
                ans -= squares[i];
            } else {
                ans += squares[i];
            }
        }

        return ans;
    }
}
