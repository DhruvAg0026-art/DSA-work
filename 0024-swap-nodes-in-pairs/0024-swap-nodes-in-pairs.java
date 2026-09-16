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
    public ListNode swapPairs(ListNode head) {
        ListNode dummy=new ListNode(-1);
        ListNode curr=dummy;
        curr.next=head;
        while(curr.next!=null && curr.next.next !=null){
           ListNode pre=curr.next;
           ListNode fur=curr.next.next;
           ListNode temp=fur.next;
           curr.next=fur;
           fur.next=pre;
           pre.next=temp;
           curr=pre;
        }
        return dummy.next;
    }
}