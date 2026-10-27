

class Solution {
    public int minBishopMoves(int[] source, int[] target) {
        int sr = source[0];
        int sc = source[1];
        int tr = target[0];
        int tc = target[1];

        // Different colored squares: impossible
        if ((sr + sc) % 2 != (tr + tc) % 2) {
            return -1;
        }

        // Both positions lie on the same diagonal
        if (Math.abs(sr - tr) == Math.abs(sc - tc)) {
            return 1;
        }

        // Same color, but different diagonals
        return 2;
    }
}
