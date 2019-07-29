package works.list;


/**
 * 双向链表
 * @author hejianglong
 * @date 2019/7/29
 */
public class SortList {

    private Node head;

    private Node tail;

    public static void main(String[] args) {
        SortList list = new SortList();
        list.put(1);
        list.put(3);
        list.put(5);
        list.put(7);
        list.put(9);
        list.print(list.head);

        SortList list2 = new SortList();
        list2.put(2);
        list2.put(4);
        list2.put(6);
        list2.put(8);
        list2.put(10);
        list.merge(list.head, list2.head);
        list.print(list.head);
    }

    public void put(Integer val) {
        Node newNode = new Node(val, null);
        if (tail == null) {
            head = tail = newNode;
            return;
        }
        Node p = head;
        Node pre = head;
        while (p != null && p.val < val) {
            pre = p;
            p = p.next;
        }
        if (p == head) {
            newNode.next = head;
            head = newNode;
            return;
        }
        pre.next = newNode;
        newNode.next = p;
    }

    public void update(Integer val, Integer newVal) {
        delete(val);
        put(newVal);
    }

    public void delete(Integer val) {
        Node p = head;
        Node pre = null;
        while (p != null) {
            if (p.val.equals(val)) {
                pre.next = p.next;
                return;
            }
            pre = p;
            p = p.next;
        }
    }

    public Node merge(Node p, Node q) {
        Node merge = null;
        if (p.val < q.val) {
            merge = p;
            p = p.next;
        } else {
            merge = q;
            q = q.next;
        }
        Node res = merge;
        while (p != null && q != null) {
            if (p.val < q.val) {
                merge.next = p;
                p = p.next;
            } else {
                merge.next = q;
                q = q.next;
            }
            merge = merge.next;
        }
        if (p != null) {
            merge.next = p;
        }
        if (q != null) {
            merge.next = q;
        }
        return res;
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
