/*
 * Project: stack
 *
 * File Created at 2019-07-06
 *
 * Copyright 2019 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */
package stack;

/**
 * @author hejianglong
 * @date 2019-07-06 09:51
 * @email hejlong@163.com
 * @Desc
 */
public class LinkedListStack<T> {

    private Node<T> head;

    public void push(T data) {
        Node currentNode = new Node(data);

        if (head == null) {
            head = currentNode;
            return;
        }
        Node currentHead = head;
        head = currentNode;
        currentNode.next = currentHead;
    }

    public T pop() {
        if (head == null) {
            throw new RuntimeException("栈中没有数据");
        }
        T returnData = head.data;
        head = head.next;
        return returnData;
    }

    private static class Node<T> {

        private T data;

        private Node next;

        public Node() {
        }

        public Node(T data) {
            this.data = data;
        }
    }
}
