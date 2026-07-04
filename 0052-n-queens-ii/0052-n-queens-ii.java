class Solution {
    int count=0;
    public int totalNQueens(int n) {
        char board[][]=new char[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(board[i],'.');
        }
        solve(n,board,0);
        return count;
    }
    void solve(int n,char[][]board,int row){
        if(row==n){
            count++;
            return;
        }
        for(int col=0;col<n;col++){
            if(issafe(board,n,row,col)){
                board[row][col]='Q';
                solve(n,board,row+1);
                board[row][col]='.';
            }
        }
    }
    boolean issafe(char[][]board,int n,int row,int col){
        for(int i=0;i<row;i++){
            if(board[i][col]=='Q')return false;
        }
        for(int i=row-1,j=col-1;i>=0&&j>=0;i--,j--){
              if(board[i][j]=='Q')return false;
        }
        for(int i=row-1,j=col+1;i>=0&&j<n;i--,j++){
              if(board[i][j]=='Q')return false;
        }
        return true;
    }
}