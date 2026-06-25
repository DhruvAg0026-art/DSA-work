class Solution {
    List <List<Integer>> ans=new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        if(nums.length<1)return ans;
         Arrays.sort(nums); 
        boolean []vis=new boolean[nums.length];
        solve(nums,vis,new ArrayList<>());
        return ans;
    }
    void solve(int []nums,boolean[]vis,List<Integer>res){
        if(res.size()==nums.length){
            ans.add(new ArrayList<>(res));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(vis[i])continue;

            if(i>0 && nums[i]==nums[i-1] && !vis[i-1])continue;

            vis[i]=true;
            res.add(nums[i]);
            solve(nums,vis,res);
            res.remove(res.size()-1);
            vis[i]=false;
        }
    }
}