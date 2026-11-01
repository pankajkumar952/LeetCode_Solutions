
class Solution {
    public int minMoves(int[] nums) {
        int min = nums[0];
        int moves = 0;

        for (int num : nums) {
            min = Math.min(min, num);
        }

        for (int num : nums) {
            moves += num - min;
        }

        return moves;
    }
}

