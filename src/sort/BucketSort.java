package sort;

/**
 * 桶排序
 */
public class BucketSort {

    public static void main(String[] args) {
        int[] arr = new int[]{22,5,11,41,45,26,29,10,7,8,30,27,42,43,40};
        sort(arr, 10);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    /**
     *
     * @param arr
     * @param bucketSize 桶容量
     */
    public static void sort(int[] arr, int bucketSize) {
        int min = arr[0];
        int max = arr[0];
        int length = arr.length;
        // 获取最大最小值
        for (int i = 0; i < length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            } else if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 获取桶
        int bucketCount = (max - min) / bucketSize + 1;
        int[][] buckets = new int[bucketCount][bucketSize];
        int[] indexArr = new int[bucketCount];

        // 填充桶数据
        for (int i = 0; i < length; i++) {
            int index = (arr[i] - min) / bucketSize;
            if (buckets[index].length == indexArr[index]) {
                // 扩容
                ensureCapacity(buckets, index);
            }
            buckets[index][indexArr[index]++] = arr[i];
        }
        int k = 0;
        // 快排对每一个桶排序
        for (int i = 0; i < buckets.length; i++) {
            // 桶里面没有数据则跳过
            if (indexArr[i] == 0) {
                continue;
            }
            // 对每一个桶里面的数据进行排序
            quickSortC(buckets[i], 0, indexArr[i] - 1);
            // 合并到原始的数组中去
            for (int j = 0; j < indexArr[i]; j++) {
                arr[k++] = buckets[i][j];
            }
        }
    }

    private static void quickSortC(int[] bucket, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = partition(bucket, p, r);
        quickSortC(bucket, p, q - 1);
        quickSortC(bucket, q + 1, r);
    }

    private static int partition(int[] bucket, int p, int r) {
        int pivot = bucket[r];
        int i = p;
        for (int j = p; j < r; j++) {
            if (bucket[j] <= pivot) {
                swap(bucket, i, j);
                i++;
            }
        }
        swap(bucket, i, r);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 数组扩容
     * @param buckets
     * @param bucketIndex
     */
    private static void ensureCapacity(int[][] buckets, int bucketIndex) {
        int[] arr = buckets[bucketIndex];
        int[] tmp = new int[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            tmp[i] = arr[i];
        }
        buckets[bucketIndex] = tmp;
    }
}
