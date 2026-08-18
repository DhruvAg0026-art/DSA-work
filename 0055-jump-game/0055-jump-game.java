class Solution {
    public boolean canJump(int[] nums) {
        int dp[]=new int [nums.length+1];
        Arrays.fill(dp,-1);
        return solve(nums,0,dp,nums.length);
    }
    boolean solve(int[]nums,int i,int []dp,int n){
        if(i==n-1){
            return true;
        }
        if(dp[i]!=-1){
            return dp[i]==1;
        }

        for(int in=1;in<=nums[i];in++){
            if(solve(nums,i+in,dp,n)==true){
                dp[i]=1;
                return true;
            }
        }
        dp[i]=0;
        return false;
    }
}