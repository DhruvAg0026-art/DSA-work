class Solution {

    char[] leftChar, rightChar;
    int[] prefix, suffix, best, len;

    public int[] longestRepeating(
            String s,
            String queryCharacters,
            int[] queryIndices) {

        int n = s.length();

        int size = 4 * n + 5;

        leftChar = new char[size];
        rightChar = new char[size];

        prefix = new int[size];
        suffix = new int[size];
        best = new int[size];
        len = new int[size];

        build(1, 0, n - 1, s);

        int[] ans = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {

            update(
                1,
                0,
                n - 1,
                queryIndices[i],
                queryCharacters.charAt(i)
            );

            ans[i] = best[1];
        }

        return ans;
    }

    void build(int node, int l, int r, String s) {

        len[node] = r - l + 1;

        if (l == r) {

            char ch = s.charAt(l);

            leftChar[node] = ch;
            rightChar[node] = ch;

            prefix[node] = 1;
            suffix[node] = 1;
            best[node] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid, s);
        build(node * 2 + 1, mid + 1, r, s);

        merge(node, node * 2, node * 2 + 1);
    }

    void update(int node, int l, int r, int index, char ch) {

        if (l == r) {

            leftChar[node] = ch;
            rightChar[node] = ch;

            prefix[node] = 1;
            suffix[node] = 1;
            best[node] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, ch);
        } else {
            update(node * 2 + 1, mid + 1, r, index, ch);
        }

        merge(node, node * 2, node * 2 + 1);
    }

    void merge(int node, int left, int right) {

        len[node] = len[left] + len[right];

        leftChar[node] = leftChar[left];
        rightChar[node] = rightChar[right];

        prefix[node] = prefix[left];
        suffix[node] = suffix[right];

        best[node] = Math.max(best[left], best[right]);

        // Boundary characters same hain
        if (rightChar[left] == leftChar[right]) {

            // Middle mein dono sequences join
            best[node] = Math.max(
                best[node],
                suffix[left] + prefix[right]
            );

            // Pura left segment same hai
            if (prefix[left] == len[left]) {
                prefix[node] =
                    len[left] + prefix[right];
            }

            // Pura right segment same hai
            if (suffix[right] == len[right]) {
                suffix[node] =
                    suffix[left] + len[right];
            }
        }
    }
}