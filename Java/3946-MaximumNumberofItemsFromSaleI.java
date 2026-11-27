class Solution {
    public int maxNumberOfItems(int[][] items, int budget) {
        int n = items.length;
        int[] bonus = new int[n];

        for (int i = 0; i < n; i++) {
            int factor = items[i][0];

            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % factor == 0) {
                    bonus[i]++;
                }
            }
        }

        int[] dp = new int[budget + 1];

        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int free = bonus[i];

            int[] activated = new int[budget + 1];
            java.util.Arrays.fill(activated, -1000000);

            for (int b = 0; b <= budget; b++) {

                if (b >= price) {
                    activated[b] = Math.max(
                        dp[b - price] + 1 + free,
                        activated[b - price] + 1
                    );
                }

                dp[b] = Math.max(dp[b], activated[b]);
            }
        }

        return dp[budget];
    }
}
