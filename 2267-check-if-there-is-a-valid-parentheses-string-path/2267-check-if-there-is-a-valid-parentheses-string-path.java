class Solution {
    public boolean hasValidPath(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // Length of path must be even
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // Starting with ')' is impossible
        if (grid[0][0] == ')') {
            return false;
        }

        boolean[][][] dp = new boolean[m][n][m + n];

        // Starting point
        dp[0][0][1] = true;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                for (int balance = 0; balance < m + n; balance++) {

                    if (!dp[i][j][balance]) {
                        continue;
                    }

                    // Move Down
                    if (i + 1 < m) {

                        int newBalance = balance;

                        if (grid[i + 1][j] == '(') {
                            newBalance++;
                        } else {
                            newBalance--;
                        }

                        if (newBalance >= 0) {
                            dp[i + 1][j][newBalance] = true;
                        }
                    }

                    // Move Right
                    if (j + 1 < n) {

                        int newBalance = balance;

                        if (grid[i][j + 1] == '(') {
                            newBalance++;
                        } else {
                            newBalance--;
                        }

                        if (newBalance >= 0) {
                            dp[i][j + 1][newBalance] = true;
                        }
                    }
                }
            }
        }

        // At destination balance must be 0
        return dp[m - 1][n - 1][0];
    }
}