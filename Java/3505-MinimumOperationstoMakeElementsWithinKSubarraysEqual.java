import java.util.*;

class Solution {
    TreeMap<Integer, Integer> low = new TreeMap<>();
    TreeMap<Integer, Integer> high = new TreeMap<>();

    long lowSum = 0, highSum = 0;
    int lowSize = 0, highSize = 0;
    int target;

    void addToLow(int val) {
        low.put(val, low.getOrDefault(val, 0) + 1);
        lowSum += val;
        lowSize++;
    }

    void addToHigh(int val) {
        high.put(val, high.getOrDefault(val, 0) + 1);
        highSum += val;
        highSize++;
    }

    void removeFromLow(int val) {
        int count = low.get(val);

        if (count == 1) low.remove(val);
        else low.put(val, count - 1);

        lowSum -= val;
        lowSize--;
    }

    void removeFromHigh(int val) {
        int count = high.get(val);

        if (count == 1) high.remove(val);
        else high.put(val, count - 1);

        highSum -= val;
        highSize--;
    }

    void rebalance() {
        int required = Math.min(target, lowSize + highSize);

        while (lowSize > required) {
            int val = low.lastKey();
            removeFromLow(val);
            addToHigh(val);
        }

        while (lowSize < required) {
            int val = high.firstKey();
            removeFromHigh(val);
            addToLow(val);
        }
    }

    void add(int val) {
        if (lowSize == 0 || val <= low.lastKey()) {
            addToLow(val);
        } else {
            addToHigh(val);
        }

        rebalance();
    }

    void remove(int val) {
        if (low.containsKey(val)) {
            removeFromLow(val);
        } else {
            removeFromHigh(val);
        }

        rebalance();
    }

    long getCost() {
        int median = low.lastKey();

        return (long) median * lowSize - lowSum
                + highSum - (long) median * highSize;
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        low.clear();
        high.clear();

        lowSum = highSum = 0;
        lowSize = highSize = 0;

        target = (x + 1) / 2;

        long[] cost = new long[n - x + 1];

        for (int i = 0; i < n; i++) {
            add(nums[i]);

            if (i >= x) {
                remove(nums[i - x]);
            }

            if (i >= x - 1) {
                cost[i - x + 1] = getCost();
            }
        }

        long INF = Long.MAX_VALUE / 4;
        long[] prev = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            long[] curr = new long[n + 1];
            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {
                curr[i] = curr[i - 1];

                if (i >= x && prev[i - x] != INF) {
                    curr[i] = Math.min(
                        curr[i],
                        prev[i - x] + cost[i - x]
                    );
                }
            }

            prev = curr;
        }

        return prev[n];
    }
}
