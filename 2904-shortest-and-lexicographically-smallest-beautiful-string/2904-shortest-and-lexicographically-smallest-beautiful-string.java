class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int n = s.length();

        int[] pos = new int[n];
        int count = 0;

        // Store positions of 1
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                pos[count++] = i;
            }
        }

        // Less than k ones
        if (count < k) {
            return "";
        }

        String ans = "";

        for (int i = 0; i + k - 1 < count; i++) {

            int start = pos[i];
            int end = pos[i + k - 1];

            String current = s.substring(start, end + 1);

            if (ans.equals("")
                    || current.length() < ans.length()
                    || (current.length() == ans.length()
                        && current.compareTo(ans) < 0)) {

                ans = current;
            }
        }

        return ans;
    }
}