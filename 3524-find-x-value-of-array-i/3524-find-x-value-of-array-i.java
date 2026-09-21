class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] ans = new long[k];

        long[] dp = new long[k];

        for (int num : nums) {

            int mod = num % k;

            long[] next = new long[k];

            // New subarray: [num]
            next[mod] = 1;

            // Extend previous subarrays
            for (int r = 0; r < k; r++) {

                int newRemainder = (int)((r * (long)mod) % k);

                next[newRemainder] += dp[r];
            }

            // Add current subarrays to final answer
            for (int r = 0; r < k; r++) {
                ans[r] += next[r];
            }

            dp = next;
        }

        return ans;
    }
}