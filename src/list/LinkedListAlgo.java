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
