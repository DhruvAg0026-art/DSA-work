import java.util.*;

class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        BitSet ans = new BitSet(2048);

        // x ^ x ^ x = x
        for (int x : nums) {
            ans.set(x);
        }

        HashSet<Integer> pairXor = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                pairXor.add(nums[i] ^ nums[j]);
            }
        }

        for (int px : pairXor) {
            for (int x : nums) {
                ans.set(px ^ x);
            }
        }

        return ans.cardinality();
    }
}