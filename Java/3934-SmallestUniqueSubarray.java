
import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        for (int len = 1; len <= n; len++) {
            HashMap<String, Integer> map = new HashMap<>();

            for (int i = 0; i + len <= n; i++) {
                StringBuilder sb = new StringBuilder();

                for (int j = i; j < i + len; j++) {
                    sb.append(nums[j]).append(',');
                }

                String key = sb.toString();

                map.put(key, map.getOrDefault(key, 0) + 1);
            }

            for (int count : map.values()) {
                if (count == 1) {
                    return len;
                }
            }
        }

        return n;
    }
}
