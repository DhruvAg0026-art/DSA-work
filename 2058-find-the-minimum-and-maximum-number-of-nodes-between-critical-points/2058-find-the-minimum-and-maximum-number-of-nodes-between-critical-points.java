/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int minDistance = Integer.MAX_VALUE;

        int first = -1;
        int previous = -1;

        int position = 1;

        ListNode prev = head;
        ListNode curr = head.next;

        while (curr != null && curr.next != null) {

            // Check critical point
            if ((curr.val > prev.val && curr.val > curr.next.val) ||
                (curr.val < prev.val && curr.val < curr.next.val)) {

                // First critical point
                if (first == -1) {
                    first = position;
                }

                // From second critical point onwards
                if (previous != -1) {
                    minDistance = Math.min(
                        minDistance,
                        position - previous
                    );
                }

                previous = position;
            }

            prev = curr;
            curr = curr.next;
            position++;
        }

        // Less than 2 critical points
        if (first == -1 || first == previous) {
            return new int[]{-1, -1};
        }

        int maxDistance = previous - first;

        return new int[]{minDistance, maxDistance};
    }
}