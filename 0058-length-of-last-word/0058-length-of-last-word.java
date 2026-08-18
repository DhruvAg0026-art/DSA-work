class Solution {
    public int lengthOfLastWord(String s) {
        String snew=s.trim();
        String[]sn=s.split(" ");
        String len=sn[sn.length-1];
        return len.length();
    }
}