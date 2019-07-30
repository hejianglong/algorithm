package works.queue;

/**
 * @author hejianglong
 * @date 2019/7/30
 */
public class ListQueue {

    private Node head;

    private Node tail;

    public static void main(String[] args) {
        ListQueue queue = new ListQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        for (int i = 0; i < 6; i++) {
            System.out.print(queue.pop() + " ");
        }
    }

    public void push(Integer val) {
        Node newNode = new Node(val, null);
        if (head == null) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    public Integer pop() {
        if (head == null) {
            throw new RuntimeException("没有数据了");
        }
        Integer result = head.val;
        head = head.next;
        return result;
    }

    static class Node {
        Integer val;

        Node next;

        public Node(Integer val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
}
