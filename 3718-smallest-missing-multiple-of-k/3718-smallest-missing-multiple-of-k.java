

class Solution {
    public int missingMultiple(int[] nums, int k) {
        
         for (int i=k;i<1000000000;i=i+k){
            boolean f=false;
                for (int j=0;j<nums.length;j++){
                    if (nums[j]==i){
                        f=true;
                        break;
                    }
                }
                if(f==false){
                    return i; 
                }
         }
         return k;
    }
}