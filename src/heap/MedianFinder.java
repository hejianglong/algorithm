package heap;

/**
 * @author hejianglong
 * @date 2019/7/16
 */
public class MedianFinder {

    Integer[] bigHeap = new Integer[32];

    Integer[] smallHeap = new Integer[32];

    int bigCount = 0;

    int smallCount = 0;

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        finder.addNum(1);
        finder.addNum(3);
        finder.addNum(4);
        finder.addNum(2);
        finder.addNum(5);
        System.out.println("中间值: " + finder.findMedian());
    }


    public double findMedian() {
        if ((bigCount + smallCount) % 2 == 0) {
            return (smallHeap[0] + bigHeap[0])/2d;
        }
        if (bigCount + smallCount == 1) {
            return smallHeap[0];
        }
        return  bigHeap[0];
    }

    public void addNum(Integer num) {
        if (bigCount + 1 == bigHeap.length || smallCount + 1 == smallHeap.length) {
            Integer[] newBigHeap = new Integer[bigHeap.length << 1];
            Integer[] newSmallHeap = new Integer[smallHeap.length << 1];
            for (int i = 0; i < bigHeap.length; i++) {
                newBigHeap[i] = bigHeap[i];
            }
            for (int i = 0; i < smallHeap.length; i++) {
                newSmallHeap[i] = smallHeap[i];
            }
            smallHeap = newSmallHeap;
            bigHeap = newBigHeap;
        }
        if (bigHeap[0] != null && num < bigHeap[0]) {
            buildBigHeap(num);
        } else {
            buildSmallHeap(num);
        }
        if (smallCount > bigCount && smallCount > 1) {
            bigHeapify(smallHeap[0]);
            removeMinHeapTop();
        } else if (bigCount - smallCount > 1) {
            smallHeapify(bigHeap[0]);
            removeBigHeapTop();
        }
    }

    private void removeMinHeapTop() {
        smallHeap[0] = smallHeap[smallCount - 1];
        smallHeap[smallCount - 1] = null;
        smallCount--;
        int i = 0;
        for (;;) {
            int minPos = i;
            if (i * 2 + 1 < smallCount && smallHeap[i] > smallHeap[i * 2 + 1]) {
                minPos = i * 2 + 1;
            }
            if (i * 2 + 2 < smallCount && smallHeap[minPos] > smallHeap[i * 2 + 2]) {
                minPos = i * 2 + 2;
            }
            if (minPos == i) {
                break;
            }
            swap(smallHeap, i, minPos);
            i = minPos;
        }
    }

    private void removeBigHeapTop() {
        bigHeap[0] = bigHeap[bigCount - 1];
        bigHeap[bigCount - 1] = null;
        bigCount--;
        int i = 0;
        for (;;) {
            int bigPos = i;
            if (i * 2 + 1 < bigCount && bigHeap[i] < bigHeap[i * 2 + 1]) {
                bigPos = i * 2 + 1;
            }
            if (i * 2 + 2 < bigCount && bigHeap[bigPos] < bigHeap[i * 2 + 2]) {
                bigPos = i * 2 + 2;
            }
            if (bigPos == i) {
                break;
            }
            swap(bigHeap, i, bigPos);
            i = bigPos;
        }
    }


    private void buildBigHeap(int num) {
        if (bigHeap[0] == null) {
            bigHeap[0] = num;
            bigCount++;
            return;
        }
        bigHeapify(num);
    }

    private void bigHeapify(int num) {
        bigHeap[bigCount++] = num;
        int i = bigCount - 1;
        while (bigCount/2 > 0 && bigHeap[i] > bigHeap[i/2]) {
            swap(bigHeap, i, i/2);
            i = i/2;
        }
    }

    private boolean buildSmallHeap(int num) {
        if (smallHeap[0] == null) {
            smallHeap[0] = num;
            smallCount++;
            return true;
        }
        smallHeapify(num);
        return true;
    }

    private void smallHeapify(int num) {
        smallHeap[smallCount++] = num;
        int i = smallCount - 1;
        while (smallCount/2 > 0 && smallHeap[i] < smallHeap[i/2]) {
            swap(smallHeap, i, i/2);
            i = i/2;
        }
    }

    private void swap(Integer[] heap, int i, int pos) {
        int tmp = heap[i];
        heap[i] = heap[pos];
        heap[pos] = tmp;
    }
}
