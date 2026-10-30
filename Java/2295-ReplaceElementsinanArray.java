import java.util.*;
class Solution {
    public int[] arrayChange(int[] nums, int[][] operations) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int[] op : operations) {

            int oldValue = op[0];
            int newValue = op[1];

            int index = map.get(oldValue);

            nums[index] = newValue;

            map.remove(oldValue);
            map.put(newValue, index);
        }

        return nums;
    }
}
