package works.stack;

/**
 * @author hejianglong
 * @date 2019/7/30
 */
public class ListStack<T> {

    private Node<T> head;

    public static void main(String[] args) {
        ListStack<Integer> stack = new ListStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);

        for (int i = 0; i < 7; i++) {
            System.out.print(stack.pop() + " ");
        }
    }

    public void push(T t) {
        Node newNode = new Node<>(t, null);
        if (head == null) {
            head  = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    public T pop() {
        if (head == null) {
            throw new RuntimeException("没有数据了");
        }
        T res = head.val;
        head = head.next;
        return res;
    }

    static class Node<T> {
        T val;

        Node<T> next;

        public Node(T val, Node<T> next) {
            this.val = val;
            this.next = next;
        }
    }
}
