class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int low = 0;
        int high = arr.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] < arr[mid + 1]) {
                // Increasing part
                // Peak right side mein hai
                low = mid + 1;
            } else {
                // Decreasing part
                // Peak mid ya left side mein hai
                high = mid;
            }
        }

        return low;
    }
}