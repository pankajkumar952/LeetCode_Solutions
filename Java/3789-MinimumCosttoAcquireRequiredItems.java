class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long both = Math.min(need1, need2);

        long costA = both * costBoth
                   + (need1 - both) * (long) cost1
                   + (need2 - both) * (long) cost2;

        long costB = Math.max(need1, need2) * (long) costBoth;

        long costC = need1 * (long) cost1
                   + need2 * (long) cost2;

        return Math.min(costA, Math.min(costB, costC));
    }
}

