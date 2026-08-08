class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int equalCnt[] = new int[n];
        int cnt = 0;
        int i=n-1;
        int j= m-1;
        char c1[] = word1.toCharArray();
        char c2[] = word2.toCharArray();
        while(i >=0 && j >=0 ){
            if(c1[i] == c2[j]){
                cnt++;
                equalCnt[i] = cnt;
                i--;
                j--;
            }else{
                equalCnt[i] = cnt;
                i--;
            }
        }

        while(i >=0){
            equalCnt[i] = cnt;
            i--;
        }
        int ans[] = new int[m];
        i=0;j=0;
        boolean changed = false;
        while(i < n && j < m){
            if(c1[i] == c2[j]){
                ans[j] = i;
                i++;
                j++;
            }else{
                if( !changed && i < n-1 && equalCnt[i+1] >= (m-j-1)){
                    ans[j] = i;
                    changed= true;
                    i++;
                    j++;
                }else{
                    i++;
                }
            }
        }

        if(j !=m){
            int res[] = {};
            return res;
        }
        return ans;
    }
}