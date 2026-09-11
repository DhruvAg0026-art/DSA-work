class Solution {
    public int totalNumbers(int[] digits) {

        int[] freq = new int[10];

        for (int d : digits) {
            freq[d]++;
        }

        int ans = 0;

        // Hundreds digit: 1-9
        for (int first = 1; first <= 9; first++) {

            if (freq[first] == 0) continue;

            freq[first]--;

            // Tens digit: 0-9
            for (int second = 0; second <= 9; second++) {

                if (freq[second] == 0) continue;

                freq[second]--;

                // Last digit must be even
                for (int third = 0; third <= 8; third += 2) {

                    if (freq[third] > 0) {
                        ans++;
                    }
                }

                freq[second]++;
            }

            freq[first]++;
        }

        return ans;
    }
}