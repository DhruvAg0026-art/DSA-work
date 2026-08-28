class Solution {

    // C++:
    // void make(string& s, int n)
    //
    // String ko palindrome banata hai
    private void makePalindrome(StringBuilder s, int n) {

        String temp = s.toString();

        if ((n & 1) == 1) {
            temp = temp.substring(0, temp.length() - 1);
        }

        StringBuilder reverse = new StringBuilder(temp);
        reverse.reverse();

        s.append(reverse);
    }


    // C++:
    // void make(string &r, const int n, vector<int>& have)
    //
    // Remaining characters ko smallest order mein fill karta hai
    private void makeSmallest(
        StringBuilder r,
        int n,
        int[] have
    ) {

        int i = 0;

        while (r.length() < n) {

            if (have[i] > 0) {
                r.append((char) ('a' + i));
                have[i]--;
            } else {
                i++;
            }
        }
    }


    public String lexPalindromicPermutation(
        String s,
        String target
    ) {

        // -----------------------------------
        // 1. Frequency of characters
        // -----------------------------------

        int[] have = new int[26];

        for (char c : s.toCharArray()) {
            have[c - 'a']++;
        }


        // -----------------------------------
        // 2. Check palindrome possible
        // -----------------------------------

        char extra = '?';

        for (int i = 0; i < 26; i++) {

            if ((have[i] & 1) == 1) {

                // More than one odd frequency
                // => palindrome impossible
                if (extra != '?') {
                    return "";
                }

                extra = (char) ('a' + i);
            }

            // We only need half of every frequency
            have[i] >>= 1;
        }


        int n = s.length();
        int m = n >> 1;


        // -----------------------------------
        // 3. Take target's first half
        // -----------------------------------

        StringBuilder temp = new StringBuilder(
            target.substring(0, m)
        );


        // mask tells which characters
        // were unavailable while constructing temp
        int mask = 0;


        // -----------------------------------
        // 4. Try to use target's first half
        // -----------------------------------

        for (int k = 0; k < temp.length(); k++) {

            int x = temp.charAt(k) - 'a';

            if (have[x]-- == 0) {
                mask |= (1 << x);
            }
        }


        // -----------------------------------
        // 5. Keep trying / backtracking
        // -----------------------------------

        while (true) {

            // --------------------------------
            // Target's half was completely made
            // --------------------------------

            if (mask == 0) {

                if (temp.length() == m) {

                    // We have exactly target's half
                    StringBuilder r = new StringBuilder(temp);

                    // Add middle character if odd length
                    if (extra != '?') {
                        r.append(extra);
                    }

                    // Make palindrome
                    makePalindrome(r, n);

                    // Strictly greater than target?
                    if (r.toString().compareTo(target) > 0) {
                        return r.toString();
                    }

                } else {

                    // temp is shorter than m
                    // Find smallest character greater
                    // than target[temp.length()]
                    int start =
                        target.charAt(temp.length()) - 'a' + 1;

                    for (int i = start; i < 26; i++) {

                        if (have[i] > 0) {

                            have[i]--;

                            StringBuilder r =
                                new StringBuilder(temp);

                            r.append((char) ('a' + i));

                            // Fill remaining half
                            makeSmallest(r, m, have);

                            // Add middle
                            if (extra != '?') {
                                r.append(extra);
                            }

                            // Make palindrome
                            makePalindrome(r, n);

                            return r.toString();
                        }
                    }
                }
            }


            // --------------------------------
            // Nothing left to backtrack
            // --------------------------------

            if (temp.length() == 0) {
                return "";
            }


            // --------------------------------
            // Backtrack last character
            // --------------------------------

            int x =
                temp.charAt(temp.length() - 1) - 'a';

            have[x]++;

            // C++:
            // if (++have[x] == 0)
            //
            // Java equivalent:
            if (have[x] == 0) {
                mask ^= (1 << x);
            }

            temp.deleteCharAt(temp.length() - 1);
        }
    }
}