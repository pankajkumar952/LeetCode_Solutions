
import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> dq = new ArrayDeque<>();

        long ans = 0;
        long cost = 0;
        int j = n - 1;

        for (int i = n - 1; i >= 0; i--) {

            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                int left = dq.pollLast();
                int right = dq.isEmpty() ? j + 1 : dq.peekLast();

                cost += (long) (right - left) * (nums[i] - nums[left]);
            }

            dq.offerLast(i);

            while (cost > k) {
                cost -= nums[dq.peekFirst()] - nums[j];

                if (dq.peekFirst() == j) {
                    dq.pollFirst();
                }

                j--;
            }

            ans += j - i + 1;
        }

        return ans;
    }
}

