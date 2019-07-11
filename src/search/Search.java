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
        int p5 = searchPositionLastLE(arr, 2);
        System.out.println(p5);
        int[] nums = new int[]{1,2,3,0};
        System.out.println(search(nums, 3));
    }

    public static int search(int[] nums, int target) {
        int split = 0;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (i < length - 1 && nums[i+1] < nums[i]) {
                split = i + 1;
                break;
            }
        }
        int[] arr = new int[length];
        int tmpSplit = split;
        if (split > 0) {
            for (int i = 0; i < length; i++) {
                if (split == length) {
                    split = 0;
                }
                arr[i] = nums[split++];
            }
        } else {
            arr = nums;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
        int low = 0;
        int height = length - 1;
        int position = -1;
        while (low <= height) {
            int middle = low + ((height - low) >> 2);
            if (target > arr[middle]) {
                low = middle + 1;
            } else if (target < arr[middle]) {
                height = middle - 1;
            } else {
                position = middle;
                break;
            }
        }
        if (position != -1) {
            position = tmpSplit + position;
            if (position > length - 1) {
                position = position - length;
            }
        }
        return position;
    }

    /**
     * 查找最后一个小于等于该值的数
     * @param arr
     * @param target
     * @return
     */
    private static int searchPositionLastLE(int[] arr, int target) {
        int low = 0;
        int height = arr.length - 1;
        while (low <= height) {
            int middle = low + ((height - low) >> 2);
            if (arr[middle] <= target) {
                if (middle == arr.length - 1 || arr[middle + 1] > target) {
                    return middle;
                }
                low = middle + 1;
            } else {
                height = middle - 1;
            }
        }
        return -1;
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
                if (middle == arr.length - 1 || arr[middle + 1] != target) {
                    return middle;
                }
                low = middle + 1;
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
