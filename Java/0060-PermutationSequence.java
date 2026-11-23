
import java.util.*;

class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        int[] fact = new int[n + 1];

        fact[0] = 1;

        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i;
            nums.add(i);
        }

        k--;

        StringBuilder result = new StringBuilder();

        for (int i = n; i >= 1; i--) {
            int index = k / fact[i - 1];

            result.append(nums.get(index));
            nums.remove(index);

            k %= fact[i - 1];
        }

        return result.toString();
    }
}

