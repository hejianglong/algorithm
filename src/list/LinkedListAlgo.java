/*
 * Project: list
 *
 * File Created at 2019-07-05
 *
 * Copyright 2019 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */
package list;

/**
 * @author hejianglong
 * @date 2019-07-05 06:41
 * @email hejlong@163.com
 * @Desc
 */
public class LinkedListAlgo {

    /**
     * 链表反转
     * @param list
     * @return
     */
    public static Node reverse(Node list) {
        Node curr = list, pre = null;
        while (curr != null) {
            Node next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;

        }
        return pre;
    }

    /**
     * 删除链表的某个节点
     * @param list
     * @param position
     * @return
     */
    public static Node deleteNode(Node list, int position) {
        if (position <= 0) {
            return list;
        }
        int i = 0;
        Node node = list;
        Node pre = null;
        if (position == 1) {
            return list.next;
        }
        while (node != null) {
            i++;
            if (i == position - 1) {
                pre = node;
            }
            if(i == position) {
               pre.next = node.next;
            }
            node = node.next;
        }
        return list;
    }

    /**
     * 删除链表倒数的第 position 个节点
     * @param list
     * @param position
     * @return
     */
    public static Node deleteLastNode(Node list, int position) {
        if (position <= 0) {
            return list;
        }
        int length = 0;
        Node node = list;
        while (node != null) {
            node = node.next;
            length++;
        }
        if (position > length) {
            return list;
        }
        // 根据倒数位置求正数的位置
        int positiveSeqPosition = length - position;
        if (positiveSeqPosition == 0) {
            return list.next;
        }
        // 倒数位置在正数后一个
        positiveSeqPosition = positiveSeqPosition + 1;
        return deleteNode(list, positiveSeqPosition);
    }

    /**
     * 寻找中间节点
     * @param list
     * @return
     */
    public static Node findMiddleNode(Node list) {
        if (list == null) return null;
        Node fast = list;
        Node slow = list;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 有序链表合并
     * @param la
     * @param lb
     * @return
     */
    public static Node mergeSortedLists(Node la, Node lb) {
        Node p = la;
        Node q = lb;
        Node head;
        if (p.data < q.data) {
            head = p;
            p = p.next;
        } else {
            head = q;
            q = q.next;
        }
        Node r = head;
        while (p != null && q != null) {
            if (p.data < q.data) {
                r.next = p;
                p = p.next;
            } else {
                r.next = q;
                q = q.next;
            }
            r = r.next;
        }
        if (p == null) {
            r.next = q;
        } else {
            r.next = p;
        }
        return head;
    }

    public static void printAll(Node list) {
        Node p = list;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static Node initTestList(int[] data) {
        Node node = null;

        for(int i = 0; i < data.length; i++) {
            if (node == null) {
                node = new Node(data[i], null);
            } else {
                Node currentNode = new Node(data[i], null);
                Node currentLastNode = findLastNode(node);
                currentLastNode.next = currentNode;
            }
        }
        return node;
    }

    private static Node findLastNode(Node node) {
        while (node.next != null) {
            node = node.next;
        }
        return node;
    }

    public static class Node {

        private int data;

        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }
}
