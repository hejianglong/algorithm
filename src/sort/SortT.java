package sort;

/**
 * 计数排序
 */
public class SortT {

    public static void main(String[] args) {
        int[] c = new int[]{2,5,3,0,2,3,0,3};
        sortJ(c);
        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i]);
        }
    }


    private static void sortJ(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int length = arr.length;
        int max = 0;
        // 找出桶最大桶
        for (int i = 0; i < length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 对应的每一个值有多少个
        int[] c = new int[max + 1];
        for (int i = 0; i < length; i++) {
            c[arr[i]]++;
        }
        // 小于等于当前值的数量
        for (int i = 1; i < c.length; i++) {
            c[i] = c[i - 1] + c[i];
        }
        int[] r = new int[length];
        // 排序
        for (int i = 0; i < length; i++) {
            int index = c[arr[i]] - 1;
            r[index] = arr[i];
            c[arr[i]]--;
        }
        // 拷贝回原来的数组
        for (int i = 0; i < r.length; i++) {
            arr[i] = r[i];
        }
    }
}
