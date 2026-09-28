class Solution {
    public ListNode swapPairs(ListNode head) {

        // Dummy node helps handle swapping from the first node
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {

            // First and second nodes
            ListNode first = prev.next;
            ListNode second = first.next;

            // Swap the nodes
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // Move prev to the end of the swapped pair
            prev = first;
        }

        return dummy.next;
    }
}
