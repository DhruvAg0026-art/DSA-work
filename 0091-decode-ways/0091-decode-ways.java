class Solution {
    public int numDecodings(String s) {
        int []dp=new int[s.length()+1];
        Arrays.fill (dp,-1);
        return solve(0,s.length(),dp,s);

    }

    int solve(int i,int n,int []dp,String s){
        if(i==n){
            return dp[i]=1;
        }
        if(dp[i]!=-1){
            return dp[i]; 
        }
        if(s.charAt(i)=='0'){
            return dp[i]=0;
        }
        int result=solve(i+1,n,dp,s);
        if(i+1<n){
            if(s.charAt(i)=='1'||(s.charAt(i)=='2'&&s.charAt(i+1)<='6')){
                result +=solve(i+2,n,dp,s);
            }
        }
        return dp[i]=result;
    }
}