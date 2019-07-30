package works.stack;

/**
 * @author hejianglong
 * @date 2019/7/30
 */
public class ArrayStack<T> {

    final static int DEFAULT_CAP = 16;

    Object[] data;

    int size = 0;

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        for (int i = 0; i < 8; i++) {
            System.out.println(stack.pop());
        }
    }

    public void push(T t) {
        if (data == null || data.length == size) {
            resize();
        }
        data[size++] = t;
    }

    public T pop() {
        if (data == null || size == 0) {
            throw new RuntimeException("没有数据了");
        }
        T res = (T) data[size - 1];
        data[--size] = null;
        return res;
    }

    private void resize() {
        if (data == null) {
            data = new Object[DEFAULT_CAP];
            return;
        }
        int oldCap = data.length;
        int newCap = oldCap << 1;
        Object[] newData = new Object[newCap];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
    }
}
