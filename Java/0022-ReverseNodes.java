class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {

        // Dummy node makes handling the first group easier
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode groupPrev = dummy;

        while (true) {

            // Find the kth node
            ListNode kth = getKthNode(groupPrev, k);

            // Fewer than k nodes remaining
            if (kth == null) {
                break;
            }

            ListNode groupNext = kth.next;

            // Reverse the current group
            ListNode prev = groupNext;
            ListNode curr = groupPrev.next;

            while (curr != groupNext) {

                ListNode next = curr.next;

                curr.next = prev;
                prev = curr;
                curr = next;
            }

            // Save the old first node.
            // It becomes the last node after reversal.
            ListNode temp = groupPrev.next;

            // Connect previous part to reversed group
            groupPrev.next = kth;

            // Move groupPrev to the end of reversed group
            groupPrev = temp;
        }

        return dummy.next;
    }

    private ListNode getKthNode(ListNode curr, int k) {

        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }

        return curr;
    }
}
