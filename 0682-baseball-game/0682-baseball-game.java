class Solution {
    public int calPoints(String[] operations) {
        Deque <Integer>st=new ArrayDeque<>();
        for(String i:operations){
            if(i.equals("+")){
                int t=st.pop();
                int sum=t+st.peek();
                st.push(t);
                st.push(sum);
            }else if(i.equals("D")){
                int du=st.peek()*2;
                st.push(du);
            }else if(i.equals("C")){
                st.pop();
            }else{
                int n=Integer.parseInt(i);
                st.push(n);
            }
        }
        int sum=0;
        while(!st.isEmpty()){
            sum += st.pop();
        }
        return sum;
    }
}