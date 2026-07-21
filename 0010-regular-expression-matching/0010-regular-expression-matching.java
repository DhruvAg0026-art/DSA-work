class Solution {
    public boolean isMatch(String s, String p) {
        Boolean [][]dp=new Boolean[s.length()+1][p.length()+1];
        return ismatch(s,p,dp,0,0);
    }
    boolean ismatch(String s,String p,Boolean[][]dp,int si,int pi){
        if(si==s.length() && pi==p.length()){
            return true;
        }

        if(pi>=p.length()){
            return false;
        }
        if(dp[si][pi]!=null){
            return dp[si][pi];
        }

        boolean charmatch=si<s.length()&&(s.charAt(si)==p.charAt(pi)||p.charAt(pi)=='.');
        boolean notachar=pi+1<p.length()&&p.charAt(pi+1)=='*';

        boolean match;
        if(charmatch){
            if(notachar){
                match=ismatch(s,p,dp,si+1,pi)||ismatch(s,p,dp,si,pi+2);
            }else{
               match= ismatch(s,p,dp,si+1,pi+1);
            }
        }else{
            if(notachar){
                match=ismatch(s,p,dp,si,pi+2);
            }else{
                match= false;
            }
        }
        dp[si][pi]=match;
        return match;
    }
}