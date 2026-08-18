class Solution {
    public int jump(int[] nums) {
        int []dp=new int[nums.length+1];
        Arrays.fill(dp,-1);
        return solve(nums,0,dp,nums.length);
    }
    int solve(int []nums,int i,int []dp,int n){
        if(i==n-1){
            return 0;
        }
        // if(nums[i]==0){
        //     return 0;
        // }
        if(dp[i]!=-1){
            return dp[i];
        }
        int min=100000000;
        for(int in=1;in<=nums[i];in++){
            if(i+in<=n-1)
            min=Math.min(min,1+solve(nums,i+in,dp,n));
            //return dp[i]=min;
        }
        return dp[i]=min;
    }
}