import java.util.*;

class Solution {
    public boolean isPossibleToRearrange(String s, String t, int k) {
        int len = s.length() / k;

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i += len) {
            String sub = s.substring(i, i + len);
            map.put(sub, map.getOrDefault(sub, 0) + 1);
        }

        for (int i = 0; i < t.length(); i += len) {
            String sub = t.substring(i, i + len);

            if (!map.containsKey(sub)) {
                return false;
            }

            map.put(sub, map.get(sub) - 1);

            if (map.get(sub) == 0) {
                map.remove(sub);
            }
        }

        return map.isEmpty();
    }
}

