
import java.util.Arrays;

class Solution {
    public long makeSimilar(int[] nums, int[] target) {
        Arrays.sort(nums);
        Arrays.sort(target);

        int n = nums.length;

        // Separate odd and even numbers.
        int[] numsEven = new int[n];
        int[] numsOdd = new int[n];
        int[] targetEven = new int[n];
        int[] targetOdd = new int[n];

        int ne = 0, no = 0, te = 0, to = 0;

        for (int x : nums) {
            if (x % 2 == 0) {
                numsEven[ne++] = x;
            } else {
                numsOdd[no++] = x;
            }
        }

        for (int x : target) {
            if (x % 2 == 0) {
                targetEven[te++] = x;
            } else {
                targetOdd[to++] = x;
            }
        }

        long diff = 0;

        // Match even numbers in sorted order.
        for (int i = 0; i < ne; i++) {
            diff += Math.abs(numsEven[i] - targetEven[i]);
        }

        // Match odd numbers in sorted order.
        for (int i = 0; i < no; i++) {
            diff += Math.abs(numsOdd[i] - targetOdd[i]);
        }

        // One operation transfers 2 between two elements.
        // Each operation fixes a total difference of 4.
        return diff / 4;
    }
}

