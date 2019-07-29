package list;

import java.util.ArrayList;

public class ListTest {

    public static void main(String[] args) {
        ListTest test = new ListTest();
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;

        ListNode listNode21 = new ListNode(5);
        ListNode listNode22 = new ListNode(6);
        ListNode listNode23 = new ListNode(4);
        listNode21.next = listNode22;
        listNode22.next = listNode23;
        ListNode listNode = test.addTwoNumbers(listNode1, listNode21);
        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
    }

    private static void singleLinkedListTest2() {
        LinkedListAlgo.Node node1 = LinkedListAlgo.initTestList(new int[]{1});
        //LinkedListAlgo.Node node2 = LinkedListAlgo.initTestList(new int[]{1,3,5,9});
        //LinkedListAlgo.Node merageNode = LinkedListAlgo.mergeSortedLists(node1, node2);
        //LinkedListAlgo.printAll(merageNode);
        //LinkedListAlgo.Node delNodel = LinkedListAlgo.deleteNode(node1, 1);
        //LinkedListAlgo.Node delNodel = LinkedListAlgo.deleteLastNode(node1, 2);
        //LinkedListAlgo.Node middleNode = LinkedListAlgo.findMiddleNode(node1);
        //LinkedListAlgo.Node delNodel = LinkedListAlgo.deleteLastKth(node1, 2);
        //LinkedListAlgo.Node circleNode = LinkedListAlgo.buildCircleNode(new int[]{2, 4, 5, 7, 9});
        //System.out.println("是否存在环: " + LinkedListAlgo.checkCircle(circleNode));
        System.out.println("是否是回文连表: " + LinkedListAlgo.isPalindrome(node1));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultNode = null;
        ListNode pre1 = null;
        ListNode pre2 = null;
        int l1length = 0;
        int l2length = 0;
        ListNode current = l1;
        ListNode currentL2 = l2;
        while (current != null) {
            ListNode nextNode = current.next;
            current.next = pre1;
            pre1 = current;
            current = nextNode;
            l1length++;
        }
        while (currentL2 != null) {
            ListNode nextNode = currentL2.next;
            currentL2.next = pre2;
            pre2 = currentL2;
            currentL2 = nextNode;
            l2length++;
        }
        if (l1length >= l2length) {
            current = pre1;
            currentL2 = pre2;
            int t = 0; // 进位值
            while (current != null) {
                int sum = current.val + t;
                if (currentL2 != null) {
                    sum += currentL2.val;
                    currentL2 = currentL2.next;
                }
                if (sum >= 10) {
                    sum = sum - 10;
                    t = 1;
                } else {
                    t = 0;
                }
                current.val = sum;
                current = current.next;
            }
            resultNode = pre1;
        } else {
            current = pre1;
            currentL2 = pre2;
            int t = 0; // 进位值
            while (currentL2 != null) {
                int sum = currentL2.val + t;
                if (current != null) {
                    sum += current.val;
                    current = current.next;
                }
                if (sum >= 10) {
                    sum = sum - 10;
                    t = 1;
                } else {
                    t = 0;
                }
                currentL2.val = sum;
                currentL2 = currentL2.next;
            }
            resultNode = pre2;
        }
        pre1 = null;
        current = resultNode;
        while (current != null) {
            ListNode nextNode = current.next;
            current.next = pre1;
            pre1 = current;
            current = nextNode;
        }
        return pre1;
    }
}
class ListNode {
      int val;
      ListNode next;
     ListNode(int x) { val = x; }
  }