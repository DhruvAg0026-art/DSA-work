class Solution {
    List <List<Integer>>ans=new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        // int len=(int)Math.pow(nums.length,nums.length);
        if(nums.length<1){
            return ans;

        }
        boolean[] vis=new boolean[nums.length];
        solve(nums,vis,new ArrayList<>());
        return ans;
    }
    void solve(int []nums,boolean[] vis,List<Integer>res){
        //base case
        if(res.size()==nums.length){
            ans.add(new ArrayList<>(res));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(vis[i]==true)continue;

            vis[i]=true;
            res.add(nums[i]);
            solve(nums,vis,res);
            res.remove(res.size()-1);
            vis[i]=false;
        }
    }
}