class Solution {
    public int stoneGameVIII(int[] stones) {

        int n = stones.length;

        // Prefix Sum
        int[] prefix = new int[n];

        prefix[0] = stones[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + stones[i];
        }

        // DP
        int[] dp = new int[n];

        // Base case
        dp[n - 1] = prefix[n - 1];

        // Fill from right to left
        for (int i = n - 2; i >= 1; i--) {

            dp[i] = Math.max(
                dp[i + 1],
                prefix[i] - dp[i + 1]
            );
        }

        return dp[1];
    }
}