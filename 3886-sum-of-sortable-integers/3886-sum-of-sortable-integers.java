import java.util.ArrayList;
import java.util.List;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        List<Integer> divisors = new ArrayList<>();
        
        // Step 1: Find all divisors of n
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
                if (i != n / i) {
                    divisors.add(n / i);
                }
            }
        }
        
        int totalSum = 0;
        
        // Step 2: Check each divisor k
        for (int k : divisors) {
            boolean possible = true;
            int prevMax = Integer.MIN_VALUE;
            
            for (int i = 0; i < n; i += k) {
                int drops = 0;
                int dropIndex = -1;
                
                // Count descents in the current block circularly
                for (int j = 0; j < k; j++) {
                    int nextJ = (j + 1) % k;
                    if (nums[i + j] > nums[i + nextJ]) {
                        drops++;
                        dropIndex = j;
                    }
                }
                
                // A block cannot be rotated if it has more than 1 descent
                if (drops > 1) {
                    possible = false;
                    break;
                }
                
                int minVal, maxVal;
                if (drops == 0) {
                    minVal = nums[i];
                    maxVal = nums[i + k - 1];
                } else {
                    minVal = nums[i + (dropIndex + 1) % k]; // Safe modular access
                    maxVal = nums[i + dropIndex];
                }
                
                // Check global non-decreasing order between consecutive blocks
                if (minVal < prevMax) {
                    possible = false;
                    break;
                }
                
                prevMax = maxVal;
            }
            
            if (possible) {
                totalSum += k;
            }
        }
        
        return totalSum;
    }
}