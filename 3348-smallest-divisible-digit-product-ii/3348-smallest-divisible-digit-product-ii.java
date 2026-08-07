import java.util.*;

class Solution {
    private static final int[][] FACTORS = {
            {0,0,0,0}, //0
            {0,0,0,0}, //1
            {1,0,0,0}, //2
            {0,1,0,0}, //3
            {2,0,0,0}, //4
            {0,0,1,0}, //5
            {1,1,0,0}, //6
            {0,0,0,1}, //7
            {3,0,0,0}, //8
            {0,2,0,0}  //9
    };

    public String smallestNumber(String num, long t) {
        int[] need = factorize(t);
        if (need == null) return "-1";

        int[] have = new int[4];
        for (char c : num.toCharArray()) {
            int d = c - '0';
            for (int i = 0; i < 4; i++)
                have[i] += FACTORS[d][i];
        }

        int zeroPos = num.indexOf('0');
        if (zeroPos == -1) {
            if (contains(have, need)) return num;
            zeroPos = num.length();
        }

        int[] prefix = have.clone();
        int n = num.length();

        for (int i = n - 1; i >= 0; i--) {
            int d = num.charAt(i) - '0';

            for (int j = 0; j < 4; j++)
                prefix[j] -= FACTORS[d][j];

            if (i > zeroPos) continue;

            int remain = n - i - 1;

            for (int nd = d + 1; nd <= 9; nd++) {
                if (nd == 0) continue;

                int[] req = new int[4];
                for (int j = 0; j < 4; j++) {
                    req[j] = Math.max(0,
                            need[j] - prefix[j] - FACTORS[nd][j]);
                }

                int[] cnt = digitCount(req);
                int len = total(cnt);

                if (len <= remain) {
                    StringBuilder ans = new StringBuilder();
                    ans.append(num, 0, i);
                    ans.append((char) ('0' + nd));

                    for (int k = 0; k < remain - len; k++)
                        ans.append('1');

                    appendDigits(ans, cnt);
                    return ans.toString();
                }
            }
        }

        int[] cnt = digitCount(need);
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < n + 1 - total(cnt); i++)
            ans.append('1');

        appendDigits(ans, cnt);
        return ans.toString();
    }

    private int[] factorize(long t) {
        int[] cnt = new int[4];
        int[] p = {2,3,5,7};

        for (int i = 0; i < 4; i++) {
            while (t % p[i] == 0) {
                cnt[i]++;
                t /= p[i];
            }
        }

        return t == 1 ? cnt : null;
    }

    private boolean contains(int[] have, int[] need) {
        for (int i = 0; i < 4; i++)
            if (have[i] < need[i]) return false;
        return true;
    }

    private int[] digitCount(int[] need) {
        int[] res = new int[10];

        int c8 = need[0] / 3;
        int r2 = need[0] % 3;

        int c9 = need[1] / 2;
        int r3 = need[1] % 2;

        int c4 = r2 / 2;
        int c2 = r2 % 2;

        int c6 = 0;

        if (c2 == 1 && r3 == 1) {
            c2 = 0;
            r3 = 0;
            c6 = 1;
        }

        if (r3 == 1 && c4 == 1) {
            c2 = 1;
            c6 = 1;
            r3 = 0;
            c4 = 0;
        }

        res[2] = c2;
        res[3] = r3;
        res[4] = c4;
        res[5] = need[2];
        res[6] = c6;
        res[7] = need[3];
        res[8] = c8;
        res[9] = c9;

        return res;
    }

    private int total(int[] cnt) {
        int s = 0;
        for (int i = 2; i <= 9; i++) s += cnt[i];
        return s;
    }

    private void appendDigits(StringBuilder sb, int[] cnt) {
        for (int d = 2; d <= 9; d++) {
            for (int i = 0; i < cnt[d]; i++)
                sb.append((char) ('0' + d));
        }
    }
}