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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;
        ListNode pre = null;
        while (curr != null) {
            if (curr.next != null && curr.val == curr.next.val) {
                int val = curr.val;
                while (curr != null && curr.val == val) {
                    curr = curr.next;
                    if (pre != null) {
                        pre.next = curr;
                    } else {
                        head = curr;
                    }
                }
            } else {
                pre = curr;
                curr = curr.next;
            }
        }
        return head;
    }
}