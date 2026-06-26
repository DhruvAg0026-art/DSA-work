class Solution {

    class BIT {
        int[] bit;
        int n;

        BIT(int n) {
            this.n = n;
            bit = new int[n + 1];
        }

        void update(int idx, int val) {
            while (idx <= n) {
                bit[idx] += val;
                idx += idx & -idx;
            }
        }

        int query(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += bit[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int offset = n + 1;

        BIT bit = new BIT(2 * n + 5);

        int prefix = 0;
        long ans = 0;

        bit.update(offset, 1);

        for (int num : nums) {
            prefix += (num == target) ? 1 : -1;

            int idx = prefix + offset;

            ans += bit.query(idx - 1);

            bit.update(idx, 1);
        }

        return ans;
    }
}