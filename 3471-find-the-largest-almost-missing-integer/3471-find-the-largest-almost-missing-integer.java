class Solution {
    public int largestInteger(int[] nums, int k) {
        HashMap<Integer,Integer> h=new HashMap<>();
        int n=nums.length;
        for(int i=0;i<=n-k;i++){
            HashSet<Integer>set=new HashSet<>();
            for(int j=i;j<i+k;j++){
                set.add(nums[j]);
            }
            for(int ij:set){
                h.put(ij,h.getOrDefault(ij,0)+1);
            }
        }
            int ans=-1;
            for( int ij:h.keySet()){
                if (h.get(ij) == 1) {
                ans = Math.max(ans, ij);
            }
            
        }
       return ans;
    }
}

 
 
 
 
 
 
 
 
 // HashMap<Integer, Integer> map = new HashMap<>();

        // int n = nums.length;

        // for (int i = 0; i <= n - k; i++) {

        //     HashSet<Integer> set = new HashSet<>();

        //     // Current window
        //     for (int j = i; j < i + k; j++) {
        //         set.add(nums[j]);
        //     }

        //     // Count each number only once per window
        //     for (int num : set) {
        //         map.put(num, map.getOrDefault(num, 0) + 1);
        //     }
        // }

        // int ans = -1;

        // for (int num : map.keySet()) {
        //     if (map.get(num) == 1) {
        //         ans = Math.max(ans, num);
        //     }
        // }

        // return ans;