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
    public boolean isPalindrome(ListNode head) {
        ListNode fast=head;
        ListNode slow=head;
        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        ListNode pre=null;
        ListNode next=null;
        while(slow!=null){
            next=slow.next;
            slow.next=pre;
            pre=slow;
            slow=next;

        }
        fast=head;
        while(pre!=null){
            if(fast.val!=pre.val)return false;
            fast=fast.next;
            pre=pre.next;
        }
        return true;
    }
}