

import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 0;
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] freq = new int[maxFactor + 1];

        for (int[] item : items) {
            freq[item[0]]++;
        }

        int[] gain = new int[maxFactor + 1];

        for (int i = 1; i <= maxFactor; i++) {
            for (int j = i; j <= maxFactor; j += i) {
                gain[i] += freq[j];
            }
            gain[i]--;
        }

        Arrays.sort(items, (a, b) -> Integer.compare(a[1], b[1]));

        long remaining = budget;
        long answer = 0;

        for (int[] item : items) {
            int factor = item[0];
            long price = item[1];

            if (price >= 2 * minPrice) {
                break;
            }

            if (gain[factor] <= 0) {
                continue;
            }

            long take = Math.min(gain[factor], remaining / price);

            answer += take * 2;
            remaining -= take * price;
        }

        answer += remaining / minPrice;

        return (int) answer;
    }
}
