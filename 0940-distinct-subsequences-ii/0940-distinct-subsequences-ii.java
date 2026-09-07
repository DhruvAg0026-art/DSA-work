class Solution {
    public int distinctSubseqII(String s) {

        final long MOD = 1_000_000_007;

        // dp[i] = number of distinct subsequences
        // using first i characters, including empty subsequence
        long[] dp = new long[s.length() + 1];

        dp[0] = 1;

        int[] last = new int[26];

        // -1 means character has not appeared yet
        java.util.Arrays.fill(last, -1);

        for (int i = 1; i <= s.length(); i++) {

            int ch = s.charAt(i - 1) - 'a';

            // Every existing subsequence:
            // 1. Don't take current character
            // 2. Take current character
            dp[i] = (2 * dp[i - 1]) % MOD;

            // If this character appeared before,
            // some subsequences are duplicate
            if (last[ch] != -1) {
                dp[i] = (dp[i] - dp[last[ch]] + MOD) % MOD;
            }

            last[ch] = i - 1;
        }

        // Remove empty subsequence
        return (int) ((dp[s.length()] - 1 + MOD) % MOD);
    }
}