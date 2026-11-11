import java.util.*;

class EventManager {
    private Map<Integer, Integer> map;
    private PriorityQueue<int[]> pq;

    public EventManager(int[][] events) {
        map = new HashMap<>();

        pq = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(b[1], a[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        for (int[] event : events) {
            map.put(event[0], event[1]);
            pq.offer(new int[]{event[0], event[1]});
        }
    }

    public void updatePriority(int eventId, int newPriority) {
        map.put(eventId, newPriority);
        pq.offer(new int[]{eventId, newPriority});
    }

    public int pollHighest() {
        while (!pq.isEmpty()) {
            int[] event = pq.poll();
            int id = event[0];
            int priority = event[1];

            if (map.containsKey(id) && map.get(id) == priority) {
                map.remove(id);
                return id;
            }
        }

        return -1;
    }
}

