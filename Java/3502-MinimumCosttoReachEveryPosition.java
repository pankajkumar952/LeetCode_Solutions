class Solution {
    public int[] minCosts(int[] cost) {
        int n = cost.length;
        int[] ans = new int[n];
        int cur = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            cur = Math.min(cur, cost[i]);
            ans[i] = cur;
        }
        return ans;
    }
}
