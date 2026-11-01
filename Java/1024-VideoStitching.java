
class Solution {
    public int videoStitching(int[][] clips, int time) {
        Arrays.sort(clips, (a, b) -> a[0] - b[0]);

        int count = 0;
        int end = 0;
        int farthest = 0;
        int i = 0;

        while (end < time) {
            while (i < clips.length && clips[i][0] <= end) {
                farthest = Math.max(farthest, clips[i][1]);
                i++;
            }

            if (farthest == end) {
                return -1;
            }

            count++;
            end = farthest;
        }

        return count;
    }
}

