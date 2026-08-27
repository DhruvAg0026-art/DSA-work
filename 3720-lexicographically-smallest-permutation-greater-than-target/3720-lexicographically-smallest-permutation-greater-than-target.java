class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();

        int[] freq = new int[26];

        // Frequency of characters in s
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        char[] ans = target.toCharArray();

        // Try to keep same prefix
        for (int i = 0; i < n; i++) {

            int index = target.charAt(i) - 'a';

            if (freq[index] > 0) {

                ans[i] = target.charAt(i);
                freq[index]--;

            } else {

                // Backtrack
                for (int j = i; j >= 0; j--) {

                    // Restore previously used character
                    if (j < i) {
                        int old = target.charAt(j) - 'a';
                        freq[old]++;
                    }

                    int current = target.charAt(j) - 'a';

                    // Find smallest character greater than target[j]
                    for (int x = current + 1; x < 26; x++) {

                        if (freq[x] > 0) {

                            ans[j] = (char) ('a' + x);
                            freq[x]--;

                            // Fill remaining characters smallest first
                            int pos = j + 1;

                            for (int c = 0; c < 26; c++) {

                                while (freq[c] > 0) {
                                    ans[pos++] = (char) ('a' + c);
                                    freq[c]--;
                                }
                            }

                            return new String(ans);
                        }
                    }
                }

                return "";
            }
        }

        // s can make exactly target,
        // but we need strictly greater.
        for (int j = n - 1; j >= 0; j--) {

            int current = target.charAt(j) - 'a';

            // Restore target[j]
            freq[current]++;

            for (int x = current + 1; x < 26; x++) {

                if (freq[x] > 0) {

                    ans[j] = (char) ('a' + x);
                    freq[x]--;

                    int pos = j + 1;

                    for (int c = 0; c < 26; c++) {
                        while (freq[c] > 0) {
                            ans[pos++] = (char) ('a' + c);
                            freq[c]--;
                        }
                    }

                    return new String(ans);
                }
            }
        }

        return "";
    }
}