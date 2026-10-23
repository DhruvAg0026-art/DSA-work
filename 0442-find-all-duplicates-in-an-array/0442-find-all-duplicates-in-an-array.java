class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ll = new ArrayList<>();
        int l = nums.length;
        for(int i = 0; i<l; i++){
            int n = Math.abs(nums[i]);
            if(nums[n-1]<0){
                ll.add(n);
            }
            else{
                nums[n-1] = -nums[n-1];
            }
        }
        return ll;
    }
}