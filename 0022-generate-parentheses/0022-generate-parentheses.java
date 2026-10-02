class Solution {

    List<String> ans = new ArrayList<>();

    public List<String> generateParenthesis(int n) {

        if (n == 0)
            return ans;

        solve(n, 0, 0, new StringBuilder());
        return ans;
    }

    public void solve(int n, int o, int c, StringBuilder s) {

        //base case
        if (s.length() == 2 * n) {
            ans.add(s.toString());
            return;
        }

        if (o < n) {
            s.append('(');
            solve(n, o + 1, c, s);
            s.deleteCharAt(s.length() - 1);
        }
        if (c < o) {
            s.append(')');
            solve(n, o, c + 1, s);
            s.deleteCharAt(s.length() - 1);
        }
    }
}