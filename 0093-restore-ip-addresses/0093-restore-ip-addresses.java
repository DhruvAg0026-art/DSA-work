class Solution {
    List <String>ans=new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        solve(s,0,0,"");
        return ans;
    }
    void solve(String s,int in,int parts,String cip){
        //base case
        if(parts ==4 && in == s.length()){
            ans.add(cip.substring(0, cip.length()-1 ));
            return;
        }
        if(parts==4&&in!=s.length()){
            return;
        }
       
        for(int i=1;i<=3;i++){
            if(in+i>s.length())break;
            String sub=s.substring(in,in+i);
            if(sub.length()>1&&sub.charAt(0)-'0'==0)continue;
            if(Integer.parseInt(sub)>255)continue;
            solve(s,in+i,parts+1,cip+sub+".");
        }
        
    }
}