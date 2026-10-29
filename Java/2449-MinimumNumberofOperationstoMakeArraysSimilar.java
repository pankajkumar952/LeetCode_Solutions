import java.util.Arrays;
class Solution {
    public long makeSimilar(int[] nums, int[] target) {
        Arrays.sort(nums);
        Arrays.sort(target);
        int n = nums.length;
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

        for (int i = 0; i < ne; i++) {
            diff += Math.abs(numsEven[i] - targetEven[i]);
        }

        for (int i = 0; i < no; i++) {
            diff += Math.abs(numsOdd[i] - targetOdd[i]);
        }
        return diff / 4;
    }
}

