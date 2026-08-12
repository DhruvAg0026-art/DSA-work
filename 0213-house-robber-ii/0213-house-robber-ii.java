class Solution {
    public int rob(int[] nums) {
        int []dp=new int [nums.length+1];
        int []dp1=new int [nums.length+1];
        if(nums.length<=1){
            return nums[0];
        }
        Arrays.fill(dp,-1);
        Arrays.fill(dp1,-1);
        return Math.max(solve(nums,dp,0,nums.length-2),solve(nums,dp1,1,nums.length-1));
    }
    int solve(int []nums,int []dp,int i,int e){
        if(i>e){
            return 0;
        }
        if(dp[i]!=-1){
            return dp[i];
        }
        dp[i]=Math.max(nums[i]+solve(nums,dp,i+2,e), solve(nums, dp, i + 1,e));
        return dp[i];
    }
}



/*class Solution {
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
}*/