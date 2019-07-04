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
        System.out.println(length);
        Node node = head;
        int i = 0;
        while (node.next!= null) {
            Node currentNode = node.next;
            Node tailNode = tail;
            Node tailPreNode = tail.pre;
            System.out.println(node.data + " -- " +tailNode.data);
            node.next = tailNode;
            tailNode.next = currentNode;
            tailPreNode.next = null;
            tail = tailPreNode;
            node = currentNode;
            if (++i == length - 1) {
                return;
            }
        }
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

        @Override
        public String toString() {
            return "Node{" +
                    "next=" + next +
                    ", data='" + data + '\'' +
                    '}';
        }
    }
}
