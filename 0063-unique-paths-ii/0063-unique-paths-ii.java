class Solution {
    public int uniquePathsWithObstacles(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);

        }
        return solve(0, 0, m, n, dp, arr);
    }

    int solve(int i, int j, int m, int n, int[][] dp, int[][] arr) {
        if (i >= m || j >= n) {
            return 0;
        }

        if (arr[i][j] == 1) {
            return 0;
        }

        if (i == m - 1 && j == n - 1) {
            return 1;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int down = solve(i + 1, j, m, n, dp, arr);
        int right = solve(i, j + 1, m, n, dp, arr);
        dp[i][j] = down + right;

        return dp[i][j];
    }
}