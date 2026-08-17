class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        // Prefix sum
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        int[][] dp = new int[n][n];

        return solve(stoneValue, prefix, dp, 0, n - 1);
    }

    int solve(int[] stones, int[] prefix, int[][] dp, int l, int r) {

        if (l == r) {
            return 0;
        }

        if (dp[l][r] != 0) {
            return dp[l][r];
        }

        int ans = 0;

        for (int k = l; k < r; k++) {

            int leftSum = prefix[k + 1] - prefix[l];
            int rightSum = prefix[r + 1] - prefix[k + 1];

            if (leftSum < rightSum) {

                ans = Math.max(
                    ans,
                    leftSum + solve(stones, prefix, dp, l, k)
                );

            } else if (rightSum < leftSum) {

                ans = Math.max(
                    ans,
                    rightSum + solve(stones, prefix, dp, k + 1, r)
                );

            } else {

                ans = Math.max(
                    ans,
                    leftSum + Math.max(
                        solve(stones, prefix, dp, l, k),
                        solve(stones, prefix, dp, k + 1, r)
                    )
                );
            }
        }

        return dp[l][r] = ans;
    }
}