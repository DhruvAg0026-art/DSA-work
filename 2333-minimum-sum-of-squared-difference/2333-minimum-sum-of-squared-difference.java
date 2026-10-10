
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2,
                                 int k1, int k2) {

        int n = nums1.length;
        int[] diff = new int[n];

        long total = 0;
        int maxDiff = 0;
        long k = (long) k1 + k2;

        // Step 1: Calculate differences
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            total += diff[i];
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        // Step 2: Enough operations to make all differences zero
        if (total <= k) {
            return 0;
        }

        // Step 3: Binary search the maximum final difference
        int left = 0;
        int right = maxDiff;

        while (left < right) {
            int mid = left + (right - left) / 2;
            long needed = 0;

            for (int d : diff) {
                if (d > mid) {
                    needed += d - mid;
                }
            }

            if (needed <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        // Step 4: Reduce every difference to at most left
        for (int i = 0; i < n; i++) {
            k -= Math.max(0, diff[i] - left);
            diff[i] = Math.min(diff[i], left);
        }

        // Step 5: Use remaining operations
        for (int i = 0; i < n && k > 0; i++) {
            if (diff[i] == left) {
                diff[i]--;
                k--;
            }
        }

        // Step 6: Calculate sum of squares
        long answer = 0;

        for (int d : diff) {
            answer += (long) d * d;
        }

        return answer;
    }
}
