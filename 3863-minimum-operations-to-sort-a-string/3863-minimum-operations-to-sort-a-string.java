
class Solution {
    public int minOperations(String s) {
        int n = s.length();

        // Check if already sorted
        boolean sorted = true;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                sorted = false;
                break;
            }
        }

        if (sorted) {
            return 0;
        }

        // Cannot sort the entire string when length is 2
        if (n == 2) {
            return -1;
        }

        char min = 'z';
        char max = 'a';

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            min = (char) Math.min(min, c);
            max = (char) Math.max(max, c);
        }

        // Minimum already at start OR maximum already at end
        if (s.charAt(0) == min || s.charAt(n - 1) == max) {
            return 1;
        }

        // Minimum or maximum exists in the middle
        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == min || s.charAt(i) == max) {
                return 2;
            }
        }

        return 3;
    }
}
