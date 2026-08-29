class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {

        int n = nums.length;

        // Store original indices
        Integer[] idx = new Integer[n];

        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }

        // Sort indices according to nums values
        Arrays.sort(idx, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] ans = new int[n];

        int i = 0;

        while (i < n) {

            int j = i + 1;

            // Find current group
            while (j < n &&
                   nums[idx[j]] - nums[idx[j - 1]] <= limit) {
                j++;
            }

            // Original indices of this group
            Integer[] group = Arrays.copyOfRange(idx, i, j);

            // Sort original indices
            Arrays.sort(group);

            // Put sorted values into sorted indices
            for (int k = i; k < j; k++) {

                int originalIndex = group[k - i];

                int value = nums[idx[k]];

                ans[originalIndex] = value;
            }

            i = j;
        }

        return ans;
    }
}