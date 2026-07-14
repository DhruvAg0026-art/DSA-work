class Solution {

    static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {

        int max = 0;

        for (int num : nums) {
            max = Math.max(max, num);
        }

        long[][] dp = new long[max + 1][max + 1];

        // Both subsequences are initially empty
        dp[0][0] = 1;

        for (int num : nums) {

            long[][] next = new long[max + 1][max + 1];

            for (int g1 = 0; g1 <= max; g1++) {

                for (int g2 = 0; g2 <= max; g2++) {

                    if (dp[g1][g2] == 0) {
                        continue;
                    }

                    long ways = dp[g1][g2];

                    // 1. Skip current number
                    next[g1][g2] =
                        (next[g1][g2] + ways) % MOD;

                    // 2. Add current number to first subsequence
                    int newG1 = gcd(g1, num);

                    next[newG1][g2] =
                        (next[newG1][g2] + ways) % MOD;

                    // 3. Add current number to second subsequence
                    int newG2 = gcd(g2, num);

                    next[g1][newG2] =
                        (next[g1][newG2] + ways) % MOD;
                }
            }

            dp = next;
        }

        long ans = 0;

        // Both subsequences must be non-empty
        // and their GCDs must be equal
        for (int g = 1; g <= max; g++) {
            ans = (ans + dp[g][g]) % MOD;
        }

        return (int) ans;
    }

    private int gcd(int a, int b) {

        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}