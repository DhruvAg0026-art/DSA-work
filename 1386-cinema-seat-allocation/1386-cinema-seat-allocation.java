import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            if (seat[1] >= 2 && seat[1] <= 9) {
                int mask = map.getOrDefault(seat[0], 0);
                mask |= 1 << (seat[1] - 2);
                map.put(seat[0], mask);
            }
        }

        int ans = (n - map.size()) * 2;

        int leftMask = 0b00001111;     // seats 2,3,4,5
        int middleMask = 0b00111100;   // seats 4,5,6,7
        int rightMask = 0b11110000;    // seats 6,7,8,9

        for (int mask : map.values()) {
            boolean left = (mask & leftMask) == 0;
            boolean middle = (mask & middleMask) == 0;
            boolean right = (mask & rightMask) == 0;

            if (left && right) {
                ans += 2;
            } else if (left || middle || right) {
                ans += 1;
            }
        }

        return ans;
    }
}