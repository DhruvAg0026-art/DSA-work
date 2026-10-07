import java.util.*;

class Solution {
    private String s;
    private Set<String> result;

    public List<String> removeInvalidParentheses(String s) {
        this.s = s;
        this.result = new HashSet<>();

        // Count the minimum number of '(' and ')' that must be removed.
        int leftRem = 0, rightRem = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftRem++;
            } else if (c == ')') {
                if (leftRem > 0) leftRem--;
                else rightRem++;
            }
        }

        dfs(0, 0, leftRem, rightRem, new StringBuilder());
        return new ArrayList<>(result);
    }

    private void dfs(int i, int open, int leftRem, int rightRem, StringBuilder sb) {
        if (i == s.length()) {
            if (open == 0 && leftRem == 0 && rightRem == 0) {
                result.add(sb.toString());
            }
            return;
        }

        char c = s.charAt(i);

        // Option 1: remove this character (only if we still need to remove that type).
        if (c == '(' && leftRem > 0) {
            dfs(i + 1, open, leftRem - 1, rightRem, sb);
        } else if (c == ')' && rightRem > 0) {
            dfs(i + 1, open, leftRem, rightRem - 1, sb);
        }

        // Option 2: keep this character.
        sb.append(c);
        if (c != '(' && c != ')') {
            dfs(i + 1, open, leftRem, rightRem, sb);
        } else if (c == '(') {
            dfs(i + 1, open + 1, leftRem, rightRem, sb);
        } else if (open > 0) {
            dfs(i + 1, open - 1, leftRem, rightRem, sb);
        }
        sb.deleteCharAt(sb.length() - 1);
    }
}