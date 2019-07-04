package list;

public class SingleLinkedList<T> {

    private int length;

    private Node<T> head;

    private Node<T> tail;

    public SingleLinkedList() {
        head = new Node<>();
    }

    public void add(T t) {
        Node currentNode = new Node(t);
        if (t == null) {
            return;
        }
        if (tail == null) {
            head.next = currentNode;
        } else {
            tail.next = currentNode;
            currentNode.pre = tail;
        }
        tail = currentNode;
        length++;
    }

    /**
     * 链表翻转
     */
    public void reverse() {
        Node newHead = new Node();
        Node newTail = new Node(tail.data);
        newHead.next = newTail;

        Node node = tail;
        while ((node = node.pre) != null) {
            Node currentNode = new Node(node.data);
            newTail.next = currentNode;
            currentNode.pre = newTail;
            newTail = currentNode;
        }
        head = newHead;
        tail = newTail;
    }

    public void printAll() {
        Node node = head;
        while ((node = node.next) != null) {
            System.out.print(node.data + ",");
        }
        System.out.println();
    }

    public void reversePrintAll() {
        Node node = tail;
        while (node != null) {
            System.out.print(node.data + ",");
            node = node.pre;
        }
        System.out.println();
    }

    private static class Node<T> {

        public Node next;

        public Node pre;

        public T data;

        public Node() {
        }

        public Node(T data) {
            this.data = data;
        }

        public Node(Node next, Node pre, T data) {
            this.next = next;
            this.pre = pre;
            this.data = data;
        }

        public static Node clone(Node beforeNode) {
            return new Node<>(beforeNode.next, beforeNode.pre, beforeNode.data);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "next=" + next +
                    ", data='" + data + '\'' +
                    '}';
        }
    }
}
