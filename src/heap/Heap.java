package heap;

/**
 * @author hejianglong
 * @date 2019/7/15
 */
public class Heap {

    public static void main(String[] args) {
        int[] a = new int[]{0,7,5,19,8,4,1,20,13,16};
        buildHeap(a, 9);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
        System.out.println();
        removeMax(a);
        System.out.println("删除最大结点");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
        System.out.println();
        sort(a, 9);
        System.out.println("排序");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
    }

    public static void sort(int[] a, int n) {
        int k = n;
        while (k > 1) {
            swap(a, 1, k);
            --k;
            heapify(a, k, 1);
        }
    }

    private static void buildHeap(int[] a, int n) {
        for (int i = n/2; i >= 1; i--) {
            heapify(a, n, i);
        }
    }

    private static void removeMax(int[] a) {
        if (a == null || a.length == 0) {
            return;
        }
        int n = a.length - 1;
        a[1] = a[n];
        heapify(a, n - 1, 1);
    }

    private static void heapify(int[] a, int n, int i) {
        for (;;) {
            int maxPos = i;
            if (i * 2 <= n && a[i] < a[i * 2]) {
                maxPos = i * 2;
            }
            if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1]) {
                maxPos = i * 2 + 1;
            }
            if (maxPos == i) {
                break;
            }
            swap(a, i, maxPos);
            i = maxPos;
        }
    }

    private static void swap(int[] a, int i, int maxPos) {
        int tmp = a[i];
        a[i] = a[maxPos];
        a[maxPos] = tmp;
    }
}
