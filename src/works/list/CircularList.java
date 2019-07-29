package works.list;

/**
 * 循环链表, curd
 * @author hejianglong
 * @date 2019/7/29
 */
public class CircularList {

    private Node head;
    private Node tail;

    public static void main(String[] args) {
        CircularList list = new CircularList();
        list.put(1);
        list.put(3);
        list.put(5);
        list.put(7);
        list.put(9);
        list.print(list.head);
        list.delete(5);
        list.print(list.head);
        list.update(1, 20);
        list.print(list.head);
    }

    public void put(Integer val) {
        Node newNode = new Node(val, null);
        if (head == null) {
            head = tail = newNode;
            tail.next = head;
            return;
        }
        tail.next = newNode;
        tail = newNode;
        tail.next = head;
    }

    public void delete(Integer val) {
        if (head.val.equals(val)) {
            head = head.next;
            tail.next = head;
            return;
        }
        Node p = head.next;
        Node pre = head;
        boolean searchSuccess = false;
        while (p != head) {
            if (p.val.equals(val)) {
                searchSuccess = true;
                break;
            }
            pre = p;
            p = p.next;
        }
        if (searchSuccess) {
            pre.next = p.next;
        }
    }

    public boolean update(Integer val, Integer newVal) {
        if (head.val.equals(val)) {
            head.val = newVal;
            return true;
        }
        Node p = head.next;
        while (p != head) {
            if (p.val.equals(val)) {
                p.val = newVal;
                return true;
            }
            p = p.next;
        }
        return false;
    }

    public void print(Node p) {
        System.out.print(p.val + " ");
        p = p.next;
        while (p != head) {
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
