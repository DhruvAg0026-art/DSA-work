import java.util.*;

class Solution {

    static class State {
        long score;
        List<Integer> ids;

        State(long score, List<Integer> ids) {
            this.score = score;
            this.ids = ids;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0); // start
            a[i][1] = intervals.get(i).get(1); // end
            a[i][2] = intervals.get(i).get(2); // weight
            a[i][3] = i;                       // original index
        }

        // Sort by end time
        Arrays.sort(a, (x, y) -> {
            if (x[1] != y[1])
                return Integer.compare(x[1], y[1]);

            return Integer.compare(x[3], y[3]);
        });

        // dp[i][k] = best answer using first i intervals
        // with at most k intervals
        State[][] dp = new State[n + 1][5];

        // Initially, choosing nothing is valid
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new State(0, new ArrayList<>());
            }
        }

        for (int i = 1; i <= n; i++) {

            int[] cur = a[i - 1];

            for (int k = 1; k <= 4; k++) {

                // 1. Don't take current interval
                State skip = dp[i - 1][k];

                // 2. Take current interval
                int prev = findPrevious(a, i - 2, cur[0]);

                State base = dp[prev + 1][k - 1];

                List<Integer> ids = new ArrayList<>(base.ids);
                ids.add(cur[3]);

                State take = new State(
                    base.score + cur[2],
                    ids
                );

                // Pick better answer
                if (better(take, skip)) {
                    dp[i][k] = take;
                } else {
                    dp[i][k] = skip;
                }
            }
        }

        List<Integer> result = new ArrayList<>(dp[n][4].ids);

        Collections.sort(result);

        int[] ans = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }

        return ans;
    }

    // Last interval whose end < current start
    private int findPrevious(int[][] a, int right, int start) {

        int lo = 0;
        int hi = right;
        int ans = -1;

        while (lo <= hi) {

            int mid = lo + (hi - lo) / 2;

            if (a[mid][1] < start) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private boolean better(State a, State b) {

        // Higher score
        if (a.score != b.score) {
            return a.score > b.score;
        }

        // Same score -> lexicographically smaller indices
        List<Integer> x = new ArrayList<>(a.ids);
        List<Integer> y = new ArrayList<>(b.ids);

        Collections.sort(x);
        Collections.sort(y);

        int len = Math.min(x.size(), y.size());

        for (int i = 0; i < len; i++) {

            if (!x.get(i).equals(y.get(i))) {
                return x.get(i) < y.get(i);
            }
        }

        return x.size() < y.size();
    }
}