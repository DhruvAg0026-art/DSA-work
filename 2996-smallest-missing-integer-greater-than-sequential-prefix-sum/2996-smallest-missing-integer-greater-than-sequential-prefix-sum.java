class Solution {
    public int missingInteger(int[] nums) {

        int sum = nums[0];

        int i = 1;

        // Sequential prefix find karo
        while (i < nums.length && nums[i] == nums[i - 1] + 1) {
            sum += nums[i];
            i++;
        }

        // Sum se greater smallest missing integer
        int ans = sum;

        while (contains(nums, ans)) {
            ans++;
        }

        return ans;
    }

    boolean contains(int[] nums, int target) {

        for (int num : nums) {
            if (num == target) {
                return true;
            }
        }

        return false;
    }
}