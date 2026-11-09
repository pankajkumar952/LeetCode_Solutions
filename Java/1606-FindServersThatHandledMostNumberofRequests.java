import java.util.*;
class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
 TreeSet<Integer> available = new TreeSet<>();
    for (int i = 0; i < k; i++) {
            available.add(i);
        }
        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );
        int[] count = new int[k];
        int maxRequests = 0;
        for (int i = 0; i < arrival.length; i++) {
            while (!busy.isEmpty() &&
                   busy.peek()[0] <= arrival[i]) {
                int[] completed = busy.poll();
                available.add(completed[1]);
            }
            if (available.isEmpty()) {
                continue;
            }
            int server = i % k;
            Integer assigned = available.ceiling(server);

            if (assigned == null) {
                assigned = available.first();
            }
            available.remove(assigned);
            count[assigned]++;
            maxRequests = Math.max(maxRequests, count[assigned]);
            busy.offer(new int[]{
                arrival[i] + load[i],
                assigned
            });
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (count[i] == maxRequests) {
                result.add(i);
            }
        }

        return result;
    }
}

