class Solution {
    public boolean isPalindrome(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;

        // Find middle
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        // Reverse second half
        ListNode prev = null;

        while(slow != null){
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        // Compare first half and reversed second half
        fast = head;

        while(prev != null){
            if(fast.val != prev.val)
                return false;

            fast = fast.next;
            prev = prev.next;
        }

        return true;
    }
}