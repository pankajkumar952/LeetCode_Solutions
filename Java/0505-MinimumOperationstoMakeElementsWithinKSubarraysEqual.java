class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];

        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> right = new PriorityQueue<>();

        long leftSum = 0, rightSum = 0;
        int leftSize = 0, rightSize = 0;

        for (int i = 0; i < x; i++) {
            left.offer(nums[i]);
            leftSum += nums[i];
            leftSize++;
        }

        while (leftSize > (x + 1) / 2) {
            int val = left.poll();
            leftSum -= val;
            leftSize--;
            right.offer(val);
            rightSum += val;
            rightSize++;
        }

        cost[0] = getCost(left, leftSum, right, rightSum);

        for (int i = 1; i <= n - x; i++) {
            int outgoing = nums[i - 1];

            if (!left.isEmpty() && outgoing <= left.peek()) {
                left.remove(outgoing);
                leftSum -= outgoing;
                leftSize--;
            } else {
                right.remove(outgoing);
                rightSum -= outgoing;
                rightSize--;
            }

            int incoming = nums[i + x - 1];

            if (left.isEmpty() || incoming <= left.peek()) {
                left.offer(incoming);
                leftSum += incoming;
                leftSize++;
            } else {
                right.offer(incoming);
                rightSum += incoming;
                rightSize++;
            }

            while (leftSize > (x + 1) / 2) {
                int val = left.poll();
                leftSum -= val;
                leftSize--;
                right.offer(val);
                rightSum += val;
                rightSize++;
            }

            while (leftSize < (x + 1) / 2) {
                int val = right.poll();
                rightSum -= val;
                rightSize--;
                left.offer(val);
                leftSum += val;
                leftSize++;
            }

            cost[i] = getCost(left, leftSum, right, rightSum);
        }

        long[][] dp = new long[k + 1][n + 1];

        for (int i = 0; i <= k; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE / 2);
        }

        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }

        for (int j = 1; j <= k; j++) {
            for (int i = x; i <= n; i++) {
                dp[j][i] = dp[j][i - 1];

                int start = i - x;
                dp[j][i] = Math.min(
                    dp[j][i],
                    dp[j - 1][start] + cost[start]
                );
            }
        }

        return dp[k][n];
    }

    private long getCost(
        PriorityQueue<Integer> left,
        long leftSum,
        PriorityQueue<Integer> right,
        long rightSum
    ) {
        int median = left.peek();

        return (long) median * left.size() - leftSum
             + rightSum - (long) median * right.size();
    }
}
