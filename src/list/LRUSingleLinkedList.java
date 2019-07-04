package list;

/**
 * LRU 单链表实现
 */
public class LRUSingleLinkedList<T> {

    /**
     * 单链表头结点
     */
    private Node<T> head;

    private int length = 0;

    /**
     * 默认链表容量为 10
     */
    private int capacity = 10;

    public LRUSingleLinkedList(Integer capacity) {
        this.head = new Node();
        this.capacity = capacity;
    }

    public void add(T val) {
        Node preNode = findPreNode(val);
        if (preNode != null) {
            // 删除原先所在位置的节点
            deleteCurrentNode(preNode);
            // 插入到头结点
            insertNodeAtBegin(val);
        } else {
            // 插入的值在缓存里面不存在
            insertNodeAtLast(val);
        }
    }

    public void print() {
        Node node = head;
        while ((node = node.next) != null) {
            System.out.print(node.data + ",");
        }
        System.out.println("----------------");
    }

    private void insertNodeAtLast(T val) {
        // 找到最后一个节点和倒数第二个节点
        Node node = head;
        Node preNode = null;
        while ((node.next) != null) {
            preNode = node;
            node = node.next;
        }
        Node currentNode = new Node(val);
        // 缓存已满的话，删除最后一个节点
        // 将新的数据插入头结点
        if (length == capacity) {
            Node headNextNode = head.next;
            currentNode.next = headNextNode;
            head.next = currentNode;
            if (preNode != null) {
                preNode.next = null;
            }
        } else {
            node.next = currentNode;
            length++;
        }
    }

    private void insertNodeAtBegin(T val) {
        Node firstNode = head.next;
        Node currentNode = new Node(val);
        currentNode.next = firstNode;
        head.next = currentNode;
        length++;
    }

    private void deleteCurrentNode(Node preNode) {
        Node waitDeleteNode = preNode.next;
        Node waitDeleteNodeNextNode = waitDeleteNode.next;
        waitDeleteNode.next = null;
        if (waitDeleteNodeNextNode != null) {
            preNode.next = waitDeleteNodeNextNode;
        } else {
            preNode.next = null;
        }
        length--;
    }

    private Node findPreNode(T val) {
        Node node = head;
        while ((node.next) != null) {
            if (val.equals(node.next.data)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    private static class Node<T> {

        public Node next;

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
