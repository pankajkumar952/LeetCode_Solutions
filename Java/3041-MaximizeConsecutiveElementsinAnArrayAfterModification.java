
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 1;

        for (int x : nums) {
            int a = dp.getOrDefault(x, 0);
            int b = dp.getOrDefault(x + 1, 0);

            dp.put(x + 1, Math.max(b, a + 1));
            dp.put(x, Math.max(a, dp.getOrDefault(x - 1, 0) + 1));

            ans = Math.max(ans, Math.max(dp.get(x), dp.get(x + 1)));
        }

        return ans;
    }
}

