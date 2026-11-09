
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

            // Preferred server
            int server = i % k;

            // Find next available server
            Integer assigned = available.ceiling(server);

            // Wrap around if necessary
            if (assigned == null) {
                assigned = available.first();
            }

            // Assign request
            available.remove(assigned);

            count[assigned]++;

            maxRequests = Math.max(maxRequests, count[assigned]);

            // Add server to busy heap
            busy.offer(new int[]{
                arrival[i] + load[i],
                assigned
            });
        }

        // Collect busiest servers
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == maxRequests) {
                result.add(i);
            }
        }

        return result;
    }
}

