class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        int[] freq = new int[max + 1];
        for (int x : nums) freq[x]++;

        long[] exact = new long[max + 1];

        // exact[d] = pairs whose gcd is exactly d
        for (int d = max; d >= 1; d--) {
            long cnt = 0;
            for (int j = d; j <= max; j += d)
                cnt += freq[j];

            long pairs = cnt * (cnt - 1) / 2;

            for (int j = d + d; j <= max; j += d)
                pairs -= exact[j];

            exact[d] = pairs;
        }

        long[] pref = new long[max + 1];
        for (int i = 1; i <= max; i++)
            pref[i] = pref[i - 1] + exact[i];

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long k = queries[i] + 1; // queries are 0-indexed

            int l = 1, r = max;

            while (l < r) {
                int mid = (l + r) / 2;
                if (pref[mid] >= k)
                    r = mid;
                else
                    l = mid + 1;
            }

            ans[i] = l;
        }

        return ans;
    }
}