package works.queue;

/**
 * @author hejianglong
 * @date 2019/7/30
 */
public class CircleQueue {

    private Node head;

    private Node tail;

    public static void main(String[] args) {
        CircleQueue queue = new CircleQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);

        for (int i = 0; i < 5; i++) {
            System.out.print(queue.pop() + " ");
        }
    }

    public void push(Integer val) {
        Node newNode = new Node(val, null);
        if (head == null) {
            head = tail = newNode;
            tail.next = head;
        }
        newNode.next = head;
        tail.next = newNode;
        tail = newNode;
    }

    public Integer pop() {
        if (head == null) {
            throw new RuntimeException("没有数据了");
        }
        Integer res = head.val;
        if (tail == head) {
            head = null;
        } else {
            tail.next = head.next;
            head = head.next;
        }
        return res;
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
