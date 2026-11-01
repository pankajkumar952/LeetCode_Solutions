
import java.util.*;

class Solution {
    public int kthSmallest(int[][] mat, int k) {
        List<Integer> sums = new ArrayList<>();
        sums.add(0);

        for (int[] row : mat) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();

            for (int sum : sums) {
                for (int num : row) {
                    pq.offer(sum + num);
                }
            }

            sums.clear();

            while (!pq.isEmpty() && sums.size() < k) {
                sums.add(pq.poll());
            }
        }

        return sums.get(k - 1);
    }
}

