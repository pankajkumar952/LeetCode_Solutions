import java.util.*;

class Solution {

    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {

            // Skip duplicate first number
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Minimum possible sum is already greater than target
            if ((long) nums[i]
                    + nums[i + 1]
                    + nums[i + 2]
                    + nums[i + 3] > target) {
                break;
            }

            // Maximum possible sum is still smaller than target
            if ((long) nums[i]
                    + nums[n - 1]
                    + nums[n - 2]
                    + nums[n - 3] < target) {
                continue;
            }

            for (int j = i + 1; j < n - 2; j++) {

                // Skip duplicate second number
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                // Minimum possible sum
                if ((long) nums[i]
                        + nums[j]
                        + nums[j + 1]
                        + nums[j + 2] > target) {
                    break;
                }

                // Maximum possible sum
                if ((long) nums[i]
                        + nums[j]
                        + nums[n - 1]
                        + nums[n - 2] < target) {
                    continue;
                }

                int left = j + 1;
                int right = n - 1;

                while (left < right) {

                    long sum = (long) nums[i]
                            + nums[j]
                            + nums[left]
                            + nums[right];

                    if (sum == target) {

                        result.add(Arrays.asList(
                                nums[i],
                                nums[j],
                                nums[left],
                                nums[right]
                        ));

                        left++;
                        right--;

                        // Skip duplicates
                        while (left < right
                                && nums[left] == nums[left - 1]) {
                            left++;
                        }

                        while (left < right
                                && nums[right] == nums[right + 1]) {
                            right--;
                        }

                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }
}
