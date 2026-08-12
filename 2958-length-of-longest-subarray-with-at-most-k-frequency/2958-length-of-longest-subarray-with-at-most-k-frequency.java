class Solution {
    public int maxSubarrayLength(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int left = 0;
        int ans = 0;

        for (int right = 0; right < nums.length; right++) {

            // Add nums[right]
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            // Agar frequency k se zyada ho gayi
            while (map.get(nums[right]) > k) {

                map.put(nums[left], map.get(nums[left]) - 1);

                left++;
            }

            // Current valid window ki length
            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}