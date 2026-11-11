import java.util.*;

class Solution {
    public String[] getFolderNames(String[] names) {
        Map<String, Integer> map = new HashMap<>();
        String[] ans = new String[names.length];

        for (int i = 0; i < names.length; i++) {
            String name = names[i];

            if (!map.containsKey(name)) {
                ans[i] = name;
                map.put(name, 1);
            } else {
                int k = map.get(name);
                String newName = name + "(" + k + ")";

                while (map.containsKey(newName)) {
                    k++;
                    newName = name + "(" + k + ")";
                }

                ans[i] = newName;
                map.put(name, k + 1);
                map.put(newName, 1);
            }
        }

        return ans;
    }
}

