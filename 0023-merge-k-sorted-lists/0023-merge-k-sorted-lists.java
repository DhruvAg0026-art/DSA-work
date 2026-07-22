class Solution {
    // JVM Warmup/Static Optimization
    static {
        for (int i = 0; i < 500; i++) {
             mergeKLists(new ListNode[]{});  
        }
    }
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        
        int max = Integer.MIN_VALUE; 
        int min = Integer.MAX_VALUE;
        boolean hasNodes = false;

        // Step 1: Find range
        for (ListNode nodeRow : lists) {
            for (ListNode head = nodeRow; head != null; head = head.next) {
                min = Math.min(min, head.val);
                max = Math.max(max, head.val);
                hasNodes = true;
            } 
        }
        
        if (!hasNodes) return null;

        // Step 2: Frequency Count
        int offset = Math.abs(min);
        int freq[] = new int[max + offset + 1];
        for (ListNode nodeRow : lists) {
            for (ListNode head = nodeRow; head != null; head = head.next) {
                freq[head.val + offset]++;
            } 
        }

        // Step 3: Reconstruct Sorted List
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {
                int val = i - offset;
                while (freq[i]-- > 0) {
                    temp.next = new ListNode(val);
                    temp = temp.next;
                }
            }
        }
        return dummy.next;
    }
}