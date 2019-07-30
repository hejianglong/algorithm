package works.queue;

/**
 * @author hejianglong
 * @date 2019/7/30
 */
public class ArrayQueue {

    final static int DEFAULT_CAP = 10;

    Integer[] data;

    int size;

    int headPosition;

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);

        for (int i = 0; i < 5; i++) {
            System.out.print(queue.pop() + " ");
        }
    }

    public void push(Integer val) {
        if (data == null || size == data.length) {
            resize();
        }
        data[size++] = val;
    }

    public Integer pop() {
        if (data == null || headPosition == size) {
            throw new RuntimeException("没有数据了");
        }
        return data[headPosition++];
    }

    private void resize() {
        if (data == null) {
            data = new Integer[DEFAULT_CAP];
            return;
        }
        int oldCap = data.length;
        int newCap = oldCap << 1;
        Integer[] newData = new Integer[newCap];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
    }
}
