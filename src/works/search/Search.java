package works.search;

/**
 * 实现一个有序数组的二分查找算法
 * 实现模糊二分查找算法（比如大于等于给定值的第一个元素）
 * @author hejianglong
 * @date 2019/8/1
 */
public class Search {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,5,7,8,9};
        int[] arr2 = {1,3,5,7,9};
        int[] arr3 = {1,2,3,4,5,5,5,8,9};
        System.out.println(searchFirstGEPosition(arr, 5));
        System.out.println(searchPositionLastLE(arr, 11));
        System.out.println(searchPositionLastMatch(arr3, 5));
        System.out.println(searchPositionFirstMatch(arr3, 8));
    }

    /**
     * 查找第一个匹配的
     * @param arr
     * @param target
     * @return
     */
    private static int searchPositionFirstMatch(int[] arr, int target) {
        int lower = 0;
        int height = arr.length - 1;
        while (lower <= height) {
            int middle = lower + ((height - lower) / 2);
            if (target < arr[middle]) {
                height = middle - 1;
            } else if (target > arr[middle]) {
                lower = middle + 1;
            } else {
                if (middle == 0 || arr[middle - 1] < target) {
                    return middle;
                }
                height = height - 1;
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
        int lower = 0;
        int height = arr.length - 1;
        while (lower <= height) {
            int middle = lower + ((height - lower) >> 1);
            if (target > arr[middle]) {
                lower = middle + 1;
            } else if (target < arr[middle]) {
                height = middle - 1;
            } else {
                if (middle == height || arr[middle + 1] != target) {
                    return middle;
                }
                int pos = middle;
                while (arr[pos] == target) {
                    pos++;
                }
                return pos - 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个小于等于该值的数
     * @param arr
     * @param target
     * @return
     */
    private static int searchPositionLastLE(int[] arr, int target) {
        int lower = 0;
        int height = arr.length - 1;
        if (target >= arr[height]) {
            return height;
        }
        while (lower <= height) {
            int middle = lower + ((height - lower) >> 1);
            if (target < arr[middle]) {
                height = middle - 1;
            } else {
                if (arr[middle] <= target && arr[middle + 1] > target) {
                    return middle;
                }
                lower = middle + 1;
            }
        }
        return -1;
    }
    /**
     * 大于等于第一个给定值
     * @param a
     * @param target
     * @return
     */
    public static int searchFirstGEPosition(int[] a, int target) {
        int lower = 0;
        int height = a.length - 1;
        while (lower <= height) {
            int middle = lower + ((height - lower) >> 1);
            if (target < a[middle]) {
                if (middle == 0 || a[middle - 1] <= target) {
                    return middle;
                }
                height = middle - 1;
            } else if (target > a[middle]) {
                lower = middle + 1;
            } else {
                return middle + 1;
            }
        }
        return -1;
    }

    public static int searchPosition(int[] a, int target) {
        int lower = 0;
        int height = a.length - 1;

        while (lower <= height) {
            int middle = lower + ((height - lower) >> 1);
            if (target < a[middle]) {
                height = middle - 1;
            } else if (target > a[middle]) {
                lower = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
}
