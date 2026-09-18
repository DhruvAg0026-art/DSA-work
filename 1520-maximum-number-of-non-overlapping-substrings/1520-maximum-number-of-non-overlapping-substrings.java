class Solution {
    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        // Initialize first occurrence
        Arrays.fill(first, n);

        // Find first and last occurrence
        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';

            first[ch] = Math.min(first[ch], i);
            last[ch] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Try every character as starting point
        for (int i = 0; i < n; i++) {

            int ch = s.charAt(i) - 'a';

            // Only first occurrence can be a starting point
            if (i != first[ch]) {
                continue;
            }

            int end = last[ch];
            boolean valid = true;

            // Expand the interval
            for (int j = i; j <= end; j++) {

                int curr = s.charAt(j) - 'a';

                // This character appeared before i
                if (first[curr] < i) {
                    valid = false;
                    break;
                }

                // Need to include all occurrences of curr
                end = Math.max(end, last[curr]);
            }

            if (valid) {
                intervals.add(new int[]{i, end});
            }
        }

        List<String> ans = new ArrayList<>();

        int prevEnd = -1;

        // Greedy selection
        for (int[] interval : intervals) {

            int start = interval[0];
            int end = interval[1];

            if (start > prevEnd) {

                ans.add(s.substring(start, end + 1));
                prevEnd = end;

            } else if (end < prevEnd) {

                // Replace previous bigger interval
                ans.set(ans.size() - 1,
                        s.substring(start, end + 1));

                prevEnd = end;
            }
        }

        return ans;
    }
}