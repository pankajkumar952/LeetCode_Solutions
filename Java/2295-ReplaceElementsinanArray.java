
import java.util.*;

class Solution {
    public int[] arrayChange(int[] nums, int[][] operations) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Store number and its index
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        // Perform replacement operations
        for (int[] op : operations) {

            int oldValue = op[0];
            int newValue = op[1];

            int index = map.get(oldValue);

            // Update array
            nums[index] = newValue;

            // Update HashMap
            map.remove(oldValue);
            map.put(newValue, index);
        }

        return nums;
    }
}
