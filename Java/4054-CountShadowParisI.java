

class Solution {
    public long countShadowPairs(int[] nums) {
        int n = nums.length;
        long ans = 0;

        java.util.TreeMap<Integer, Integer> map = new java.util.TreeMap<>();

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            Integer lower = map.lowerKey(x);

            if (lower != null) {
                ans += map.headMap(x, false).values()
                         .stream()
                         .mapToInt(Integer::intValue)
                         .sum();
            }

            map.merge(x, 1, Integer::sum);
        }

        return ans;
    }
}
