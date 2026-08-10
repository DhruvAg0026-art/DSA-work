class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int []dp=new int [cost.length+1];
        Arrays.fill(dp,-1);
        return Math.min(solve(0, cost, dp),solve(1, cost, dp));
    }
    int solve(int i,int[]c,int []dp){
        if(i>=c.length){
            return 0;
        }
        if(dp[i]!=-1){
            return dp[i];
        }
        dp[i]=c[i]+Math.min(solve(i+1,c,dp),solve(i+2,c,dp));
        return dp[i];
    }
}