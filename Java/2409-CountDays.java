class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice,
                                 String arriveBob, String leaveBob) {
        int start = Math.max(toDays(arriveAlice), toDays(arriveBob));
        int end = Math.min(toDays(leaveAlice), toDays(leaveBob));
        return Math.max(0, end - start + 1);
    }
    private int toDays(String date) {
        int[] daysInMonth = {
            31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
        };
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        int totalDays = day;

        for (int i = 0; i < month - 1; i++) {
            totalDays += daysInMonth[i];
        }

        return totalDays;
    }
}
