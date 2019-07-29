package works.list;

/**
 * 单链表,curd
 * 单链表反转
 * @author hejianglong
 * @date 2019/7/29
 */
public class SingleList {

    private Node head;

    private Node tail;

    public static void main(String[] args) {
        SingleList list = new SingleList();
        list.add(10);
        list.add(5);
        list.add(7);
        list.add(4);
        list.add(9);
        list.add(8);
        list.print(list.head);
        list.update(5, 15);
        list.print(list.head);
        list.delete(7);
        list.print(list.head);
        System.out.println("链表翻转");
        Node head = list.reverse();
        list.print(head);
        Node middle = list.findMiddleNode();
        System.out.println("中间值: " + middle.val);
    }

    private void add(Integer val) {
        if (tail == null) {
            head = tail = new Node(val, null);
            return;
        }
        Node newNode = new Node(val, null);
        tail.next = newNode;
        tail = newNode;
    }

    public void delete(Integer val) {
        Node p = head;
        Node pre = null;
        while (!p.val.equals(val)) {
            pre = p;
            p = p.next;
        }
        pre.next = p.next;
        if (p == tail) {
            tail = pre;
        }
    }

    public void update(Integer val, Integer newVal) {
        Node p = head;
        while (!p.val.equals(val)) {
            p = p.next;
        }
        p.val = newVal;
    }

    public Node reverse() {
        Node p = head;
        Node pre = null;
        while (p != null) {
            Node nextNode = p.next;
            p.next = pre;
            pre = p;
            p = nextNode;
        }
        head = pre;
        return pre;
    }

    public Node findMiddleNode() {
        Node fast = head;
        Node slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public void print(Node p) {
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
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
