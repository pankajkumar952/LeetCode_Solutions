
class Solution {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int clockwise = 0;
        int total = 0;

        for (int d : distance) {
            total += d;
        }

        int left = Math.min(start, destination);
        int right = Math.max(start, destination);

        for (int i = left; i < right; i++) {
            clockwise += distance[i];
        }

        int counterclockwise = total - clockwise;

        return Math.min(clockwise, counterclockwise);
    }
}

