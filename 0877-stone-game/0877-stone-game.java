class Solution {
    public boolean stoneGame(int[] piles) {
        Integer[][] dp = new Integer[piles.length][piles.length];

        int diff = solve(piles, 0, piles.length - 1, dp);

        return diff > 0;
    }

    private int solve(int[] piles, int left, int right, Integer[][] dp) {

        if (left == right) {
            return piles[left];
        }

        if (dp[left][right] != null) {
            return dp[left][right];
        }

        int pickLeft = piles[left] 
                     - solve(piles, left + 1, right, dp);

        int pickRight = piles[right] 
                      - solve(piles, left, right - 1, dp);

        return dp[left][right] = Math.max(pickLeft, pickRight);
    }
}