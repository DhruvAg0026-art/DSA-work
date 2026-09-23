class Solution {
    public int minOperations(int[] nums, int x) {

        int n = nums.length;

        // Total sum of array
        int total = 0;

        for (int num : nums) {
            total += num;
        }

        // We need a subarray with this sum
        int target = total - x;

        // If target is negative, impossible
        if (target < 0) {
            return -1;
        }

        // target = 0 means remove entire array
        if (target == 0) {
            return n;
        }

        int left = 0;
        int sum = 0;
        int maxLen = -1;

        for (int right = 0; right < n; right++) {

            sum += nums[right];

            // Window sum became too large
            while (sum > target) {
                sum -= nums[left];
                left++;
            }

            // Found required sum
            if (sum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        // No such subarray
        if (maxLen == -1) {
            return -1;
        }

        return n - maxLen;
    }
}