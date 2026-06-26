class Solution {
    List <List<Integer>> ans=new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        solve(nums,new ArrayList<>(),0);
        return ans;
        
    }
    void solve(int[]nums,List<Integer>res,int in){
        ans.add(new ArrayList<>(res));
        for(int i=in;i<nums.length;i++){
            res.add(nums[i]);
            solve(nums,res,i+1);
            res.remove(res.size()-1);
        }
    }
}