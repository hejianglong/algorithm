package search;

public class Search {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
        int position = searchPosition(arr, 1);
        System.out.println(position);
        int[] arr2 = new int[]{1,2,3,4,5,8,8,8,8,9, 10, 11,12,13,14,15,16,17,18};
        int p2 = searchPositionFirstMatch(arr2, 8);
        System.out.println(p2);
    }

    /**
     * 查找第一个匹配的
     * @param arr
     * @param target
     * @return
     */
    private static int searchPositionFirstMatch(int[] arr, int target) {
        int low = 0;
        int height = arr.length - 1;

        while (low <= height) {
            int middle = low + ((height - low) >> 1);
            if (target < arr[middle]) {
                height = middle - 1;
            } else if (target > arr[middle]) {
                low = middle + 1;
            } else {
                if (middle == 0 || arr[middle - 1] != target) {
                    return middle;
                }
                height = middle - 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找
     * @param arr
     * @param target
     * @return
     */
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
