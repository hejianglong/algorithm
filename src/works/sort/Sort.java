package works.sort;

/**
 *
 * 归并排序、快速排序、插入排序、冒泡排序、选择排序
 * @author hejianglong
 * @date 2019/7/31
 */
public class Sort {

    public static void main(String[] args) {
        int[] arr = {4,5,6,3,2,1};
        quickSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    public static void mpSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    public static void insertSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[j] > value) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = value;
        }
    }

    public static void gbSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int p, int r) {
        if (p >= r) {
            return;
        }
        int m = p + (r - p) / 2;
        mergeSort(arr, p, m);
        mergeSort(arr, m + 1, r);
        merge(arr, p, m, r);
    }

    private static void merge(int[] arr, int p, int m, int r) {
        int[] tmp = new int[r - p + 1];
        int i = p;
        int j = m + 1;
        int k = 0;
        while (i <= m && j <= r) {
            if (arr[i] > arr[j]) {
                tmp[k++] = arr[j++];
            } else {
                tmp[k++] = arr[i++];
            }
        }
        int start = i;
        int end = m;
        if (j <= r) {
            start = j;
            end = r;
        }
        while (start <= end) {
            tmp[k++] = arr[start++];
        }
        for (i = 0; i <= r - p; i++) {
            arr[p + i] = tmp[i];
        }
    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int p, int r) {
        if (p >= r) {
            return;
        }
        int partition = partition(arr, p, r);
        quickSort(arr, p, partition - 1);
        quickSort(arr, partition + 1, r);
    }

    private static int partition(int[] arr, int p, int r) {
        int pivot = arr[r];
        int i = p;
        for (int j = p; j < r; j++) {
            if (arr[j] < pivot) {
                if (i == j) {
                    i++;
                } else {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        int tmp = arr[i];
        arr[i] = pivot;
        arr[r] = tmp;
        return i;
    }
}
