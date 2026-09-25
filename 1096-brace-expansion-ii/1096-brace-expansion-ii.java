import java.util.*;

class Solution {

    public List<String> braceExpansionII(String expression) {
        Set<String> result = solve(expression, 0, expression.length() - 1);

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    private Set<String> solve(String s, int l, int r) {

        Set<String> result = new HashSet<>();
        Set<String> current = new HashSet<>();
        current.add("");

        int i = l;

        while (i <= r) {

            char ch = s.charAt(i);

            // -------------------------
            // CASE 1: Normal character
            // -------------------------
            if (ch >= 'a' && ch <= 'z') {

                Set<String> next = new HashSet<>();

                for (String str : current) {
                    next.add(str + ch);
                }

                current = next;
                i++;
            }

            // -------------------------
            // CASE 2: Opening brace
            // -------------------------
            else if (ch == '{') {

                int count = 1;
                int j = i + 1;

                while (count > 0) {
                    if (s.charAt(j) == '{') {
                        count++;
                    } else if (s.charAt(j) == '}') {
                        count--;
                    }
                    j++;
                }

                // j-1 is closing brace
                Set<String> inside = solve(s, i + 1, j - 2);

                // Concatenate current with inside
                Set<String> next = new HashSet<>();

                for (String a : current) {
                    for (String b : inside) {
                        next.add(a + b);
                    }
                }

                current = next;

                i = j;
            }

            // -------------------------
            // CASE 3: Comma
            // -------------------------
            else if (ch == ',') {

                result.addAll(current);

                current = new HashSet<>();
                current.add("");

                i++;
            }
        }

        // Add last part
        result.addAll(current);

        return result;
    }
}