
import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;

        // Step 1: Convert every element into its square
        for (int i = 0; i < n; i++) {
            nums[i] *= nums[i];
        }

        // Step 2: Sort the squares in ascending order
        Arrays.sort(nums);

        // Step 3: Subtract the smaller half and add the larger half
        long ans = 0;
        int mid = n / 2;

        for (int i = 0; i < mid; i++) {
            ans -= nums[i];
        }

        for (int i = mid; i < n; i++) {
            ans += nums[i];
        }

        return ans;
    }
}
