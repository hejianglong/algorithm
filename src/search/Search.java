package search;

public class Search {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
        int position = searchPosition(arr, 1);
        System.out.println(position);
    }

    private static int searchPosition(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        while (end != start) {
            int middle = (end - start) / 2 + start;
            if (target == arr[middle]) {
                return middle;
            }
            if (target == arr[start]) {
                return start;
            }
            if (target == arr[end]) {
                return end;
            }
            if (target < arr[middle]) {
                end = middle;
                continue;
            }
            if (target > arr[middle]) {
                start = middle;
                continue;
            }
        }
        return -1;
    }
}
