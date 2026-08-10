class Solution {
    public int rob(int[] nums) {
        int []dp=new int [nums.length+1];
        Arrays.fill(dp,-1);
        return Math.max(solve(nums,dp,0),solve(nums,dp,1));

    }
    int solve(int []nums,int []dp,int i){
        if(i>=nums.length){
            return 0;
        }
        if(dp[i]!=-1){
            return dp[i];
        }
        dp[i]=Math.max(nums[i]+solve(nums,dp,i+2), solve(nums, dp, i + 1));
        return dp[i];
    }
}