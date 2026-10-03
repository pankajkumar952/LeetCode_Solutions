import java.util.PriorityQueue;

class Solution {

    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.val, b.val)
        );

        // Put the first node of every non-empty list into the heap
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        // Dummy node
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {

            // Get the smallest node
            ListNode current = pq.poll();

            // Add it to the result
            tail.next = current;
            tail = tail.next;

            // Add the next node from the same list
            if (current.next != null) {
                pq.offer(current.next);
            }
        }

        return dummy.next;
    }
}
