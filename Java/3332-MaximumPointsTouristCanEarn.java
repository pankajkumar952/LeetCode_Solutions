class Solution {
    public int maxScore(int n, int k, int[][] stayScore, int[][] travelScore) {
        int[] dp = new int[n];

        for (int day = 0; day < k; day++) {
            int[] next = new int[n];

            for (int curr = 0; curr < n; curr++) {

                next[curr] = Math.max(
                    next[curr],
                    dp[curr] + stayScore[day][curr]
                );

                for (int dest = 0; dest < n; dest++) {
                    if (curr != dest) {
                        next[dest] = Math.max(
                            next[dest],
                            dp[curr] + travelScore[curr][dest]
                        );
                    }
                }
            }

            dp = next;
        }

        int ans = 0;

        for (int points : dp) {
            ans = Math.max(ans, points);
        }

        return ans;
    }
}
