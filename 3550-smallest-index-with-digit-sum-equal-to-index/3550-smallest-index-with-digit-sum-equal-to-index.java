class Solution {
    public int smallestIndex(int[] nums) {

        for (int i = 0; i < nums.length; i++) {

            int num = nums[i];
            int sum = 0;

            // Digit sum
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }

            // Digit sum == index
            if (sum == i) {
                return i;
            }
        }

        return -1;
    }
}