class Solution {
    public boolean isValid(String s) {
        Stack <Character>st=new Stack<>();
        if(s.length()==0)return false;
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            
            if(c=='('||c=='{'||c=='['){st.push(c);
            }else{
                if(st.isEmpty())return false;
                char p=st.pop();
                if(c==')'&&p!='('||c=='}'&&p!='{'||c==']'&&p!='[')return false;
            }
        }
        return st.isEmpty();
    }
}