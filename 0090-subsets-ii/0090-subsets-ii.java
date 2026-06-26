class Solution {
    List<List<Integer>> ans=new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        solve(nums,0,new ArrayList<>());
        return ans;
    }
    void solve (int[]nums,int in,List<Integer>res){
        ans.add(new ArrayList<>(res));
        for(int i=in;i<nums.length;i++){
            if(i>in && nums[i-1]==nums[i])continue;

            res.add(nums[i]);
            solve(nums,i+1,res);
            res.remove(res.size()-1);
        }
    }
}