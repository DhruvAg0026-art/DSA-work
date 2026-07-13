class Solution {
    public List<Integer> sequentialDigits(int low, int high) {

        List<Integer> ans = new ArrayList<>();

        String digits = "123456789";

        for (int len = 2; len <= 9; len++) {

            for (int start = 0; start + len <= 9; start++) {

                String sub = digits.substring(start, start + len);

                int num = Integer.parseInt(sub);

                if (num >= low && num <= high) {
                    ans.add(num);
                }
            }
        }

        return ans;
    }
}