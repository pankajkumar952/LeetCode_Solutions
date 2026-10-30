import java.util.*;
class Solution {
    public long beautifulSubarrays(int[] nums) {

        HashMap<Integer, Long> map = new HashMap<>();

        int prefixXor = 0;
        long count = 0;

        map.put(0, 1L);

        for (int num : nums) {

            prefixXor ^= num;

            // Count previous occurrences
            count += map.getOrDefault(prefixXor, 0L);

            // Store current prefix XOR
            map.put(prefixXor,
                    map.getOrDefault(prefixXor, 0L) + 1);
        }

        return count;
    }
}
