package search;

public class Search {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
        int position = searchPosition(arr, 8);
        System.out.println(position);
        int[] arr2 = new int[]{1,2,3,4,5,8,8,8,8,9, 10, 11,12,13,14,15,16,17,18};
        int p2 = searchPositionFirstMatch(arr2, 8);
        System.out.println(p2);
        int p3 = searchPositionLastMatch(arr2, 8);
        System.out.println(p3);
        int p4 = searchPositionFirstGE(arr2, 8);
        System.out.println(p4);
    }

    /**
     * 查找第一个大于等于 target
     * @param arr
     * @param target
     * @return
     */
    private static int searchPositionFirstGE(int[] arr, int target) {
        int low = 0;
        int height = arr.length - 1;
        while (low <= height) {
            int middle = low + ((height - low) >> 2);
            if (arr[middle] >= target) {
                if (middle == 0 || arr[middle - 1] < target) {
                    return middle;
                }
                height = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个匹配的
     * @param arr
     * @param target
     * @return
     */
    private static int searchPositionLastMatch(int[] arr, int target) {
        int low = 0;
        int height = arr.length - 1;
        while (low <= height) {
            int middle = low + ((height - low) >> 1);
            if (target > arr[middle]) {
                low = middle + 1;
            } else if (target < arr[middle]) {
                height = middle + 1;
            } else {
                if (middle == 0 || arr[middle + 1] != target) {
                    return middle;
                }
                height += 1;
            }
        }
        return -1;
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
        int low = 0;
        int height = arr.length - 1;
        while (low <= height) {
            int middle = low + ((height - low) >> 1);
            if (target < arr[middle]) {
                height = middle - 1;
            } else if (target > arr[middle]) {
                low = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
}
