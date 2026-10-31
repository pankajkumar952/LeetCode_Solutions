class BusBooking {

    private boolean[][] booked;
    private int[][] cost;

    private int totalTime;
    private int bookedCount;

    public BusBooking(int n) {
        booked = new boolean[n + 1][4];
        cost = new int[n + 1][4];
    }

    public void toggle(String seat) {
        int row = Integer.parseInt(seat.substring(1));
        int col = seat.charAt(0) - 'A';

        if (booked[row][col]) {
            // Free seat
            totalTime -= cost[row][col];
            bookedCount--;

            booked[row][col] = false;
            cost[row][col] = 0;

        } else {
            // Book seat
            int time = 1;

            if (col == 0 && booked[row][1]) {
                time = 3;
            }

            if (col == 3 && booked[row][2]) {
                time = 3;
            }

            booked[row][col] = true;
            cost[row][col] = time;

            totalTime += time;
            bookedCount++;
        }
    }

    public int getTotalTime() {
        return totalTime;
    }

    public int getMinTime() {
        return bookedCount;
    }
}
