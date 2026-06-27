class Solution {
    public int maximumLength(int[] nums) {
        HashMap<Long, Integer> map = new HashMap<>();

        for (int x : nums) {
            map.put((long) x, map.getOrDefault((long) x, 0) + 1);
        }

        int ans = 0;

        // Handle 1 separately
        if (map.containsKey(1L)) {
            int ones = map.get(1L);
            ans = (ones % 2 == 0) ? ones - 1 : ones;
        }

        for (long x : map.keySet()) {
            if (x == 1) continue;

            long cur = x;
            int pairs = 0;

            while (map.getOrDefault(cur, 0) >= 2) {
                pairs++;
                cur = cur * cur;
            }

            if (map.getOrDefault(cur, 0) == 1) {
                ans = Math.max(ans, 2 * pairs + 1);
            } else {
                ans = Math.max(ans, 2 * pairs - 1);
            }
        }

        return ans;
    }
}