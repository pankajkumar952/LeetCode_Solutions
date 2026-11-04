
import java.util.*;

class Solution {

    static class Node {
        long value;
        int l, r;

        Node(long value, int l, int r) {
            this.value = value;
            this.l = l;
            this.r = r;
        }
    }

    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;

        int[] log = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            log[i] = log[i / 2] + 1;
        }

        int levels = log[n] + 1;

        int[][] maxST = new int[levels][n];
        int[][] minST = new int[levels][n];

        for (int i = 0; i < n; i++) {
            maxST[0][i] = nums[i];
            minST[0][i] = nums[i];
        }

        for (int j = 1; j < levels; j++) {
            int half = 1 << (j - 1);
            int len = 1 << j;

            for (int i = 0; i + len <= n; i++) {
                maxST[j][i] = Math.max(
                    maxST[j - 1][i],
                    maxST[j - 1][i + half]
                );

                minST[j][i] = Math.min(
                    minST[j - 1][i],
                    minST[j - 1][i + half]
                );
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(b.value, a.value)
        );

        for (int l = 0; l < n; l++) {
            long value = getValue(l, n - 1, log, maxST, minST);
            pq.offer(new Node(value, l, n - 1));
        }

        long answer = 0;

        for (int i = 0; i < k; i++) {
            Node cur = pq.poll();

            answer += cur.value;

            if (cur.r > cur.l) {
                int newR = cur.r - 1;

                long value = getValue(
                    cur.l, newR, log, maxST, minST
                );

                pq.offer(new Node(value, cur.l, newR));
            }
        }

        return answer;
    }

    private long getValue(
        int l,
        int r,
        int[] log,
        int[][] maxST,
        int[][] minST
    ) {
        int len = r - l + 1;
        int j = log[len];

        int max = Math.max(
            maxST[j][l],
            maxST[j][r - (1 << j) + 1]
        );

        int min = Math.min(
            minST[j][l],
            minST[j][r - (1 << j) + 1]
        );

        return (long) max - min;
    }
}

