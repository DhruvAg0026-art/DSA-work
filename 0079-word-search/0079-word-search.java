class Solution {
    public boolean exist(char[][] board, String word) {
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(solve(board,word,i,j,0))return true;
            }
        }
        return false;
    }
    boolean solve(char[][]boa,String wrd,int i,int j,int in){
        if(in == wrd.length())return true;

        if(i<0||j<0||i>=boa.length||j>=boa[0].length||boa[i][j]!=wrd.charAt(in))return false;

        char temp=boa[i][j];
        boa[i][j]='#';

        boolean found=(solve(boa,wrd,i+1,j,in+1)||
        solve(boa,wrd,i-1,j,in+1)||solve(boa,wrd,i,j+1,in+1)||solve(boa,wrd,i,j-1,in+1));
        boa[i][j]=temp;
        return found;
    }
}