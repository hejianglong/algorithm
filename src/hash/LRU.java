/*
 * Project: hash
 *
 * File Created at 2019-07-13
 *
 * Copyright 2019 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */
package hash;

/**
 * @author hejianglong
 * @date 2019-07-13 14:49
 * @email hejlong@163.com
 * @Desc
 */
public class LRU<K,V> {

    private Node<K, V> head;

    private Node<K, V> tail;

    // 默认数组容量
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    // 容量
    private int capacity;

    // 装载因子
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Node<K, V>[] table;

    private int size;

    // 可以扩容槽位的值
    int threshold;

    public LRU(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("容量必须大于 0");
        }
        this.capacity = capacity;
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public V put(K key, V val) {
        return putVal(hash(key), key, val);
    }

    public V find(K key) {
        Node<K, V> node = findNode(hash(key), key);
        return node == null ? null : node.val;
    }

    private Node<K, V> findNode(int hash, K key) {
        if (table == null || table.length == 0) {
            return null;
        }
        int index = (table.length - 1) & hash;
        Node<K, V> indexNode = table[index];
        if (indexNode == null) {
            return null;
        }

        Node<K, V> currentNode = indexNode;
        while (currentNode != null) {
            if (currentNode.hash == hash && (currentNode.key == key || currentNode.key.equals(key))) {
                break;
            }
            currentNode = currentNode.hNext;
        }
        if (currentNode == null) {
            return null;
        }
        if (currentNode.pre == null) {
            head = currentNode.next;
        } else {
            currentNode.pre.next = currentNode.next;
            if (currentNode.next != null) {
                currentNode.next.pre = currentNode.pre;
            }
        }

        tail.next = currentNode;
        currentNode.pre = tail;
        currentNode.next = null;
        tail = currentNode;
        return currentNode;
    }

    private V putVal(int hash, K key, V val) {
        int index = 0;
        if (table == null || table.length == 0) {
            resize();
            index = (table.length - 1) & hash;
            Node<K, V> node = (Node<K, V>) new Node(index, key, val, hash);
            table[index] = node;
            head = node;
            tail = head;
            ++size;
            return null;
        }
        index = (table.length - 1) & hash;
        final Node<K, V> newNode = (Node<K, V>) new Node(index, key, val, hash);

        final Node<K, V> indexNode = table[index];
        if (indexNode == null) {
            table[index] = newNode;
            tail.next = newNode;
            newNode.pre = tail;
            tail = newNode;
            ++size;
            return null;
        } else {
            Node<K, V> node = indexNode;
            Node<K, V> hPre = null;
            while (node != null) {
                hPre = node;
                node = node.hNext;
            }
            // 找到了对应的 key 搬移到最后一个去
            if (node != null) {
                node.pre.next = node.next;
                node.next.pre = node.pre;

                tail.next = node;
                node.pre = tail;
                node.next = null;
                tail = node;
            } else {
                // 插入新的时候，检查容量是否已经满了
                // 满了删除头结点，将数据插入到尾结点
                if (size >= capacity) {
                    // 水平删除 head 结点, 并连接前后
                    Node<K, V> hPreHeaderNode = null;
                    // 水平方向最后一个结点
                    Node<K, V> hNode = table[head.index];
                    if (hNode == null) {
                        int a = 1;
                    }
                    boolean searchSuccess = (hNode.hash != head.hash ? false : true);

                    // 找到 head 结点
                    while (hNode != null) {
                        if (!searchSuccess && hNode.next != null && hNode.next.hash == head.hash && (hNode.next.key == head.key || hNode.next.key.equals(head.key))) {
                            hPreHeaderNode = hNode;
                            searchSuccess = true;
                        }
                        hNode = hNode.hNext;
                    }
                    // 没有找到的时候，插入水平尾结点，删除双向链表头结点
                    if (hNode == null) {
                        // 水平链表加入
                        hPre.hNext = newNode;
                        // 水平链删除
                        if (hPreHeaderNode != null) {
                            hPreHeaderNode.hNext = head.hNext;
                        } else {
                            table[head.index] = head.hNext;
                        }
                        // 双向链表删除
                        head.next.pre = null;
                        head = head.next;
                    } else {
                        // 找到了则在双向链表中将其删除
                        hNode.pre.next = hNode.next;
                        hNode.next.pre = hNode.pre;
                    }
                    // 插入尾结点
                    tail.next = newNode;
                    newNode.pre = tail;
                    tail = newNode;
                } else {
                    // 容量未满直接插入
                    hPre.hNext = newNode;
                    tail.next = newNode;
                    newNode.pre = tail;
                    tail = newNode;
                }
            }
        }
        // 达到扩容标准
        if (++size >= threshold && size <= table.length) {
            resize();
        }
        return null;
    }

    private void resize() {
        int newCapacity = 0;
        if (table == null || table.length == 0) {
            newCapacity = capacity > DEFAULT_INITIAL_CAPACITY ? DEFAULT_INITIAL_CAPACITY : capacity;
            table = (Node<K, V>[]) new Node[newCapacity];
            threshold = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
            size = 0;
            return;
        }
        int oldCapacity = table.length;
        if (oldCapacity >= capacity) {
            return;
        }
        newCapacity = (oldCapacity << 1);
        threshold = threshold << 1;
        int reduceCap = newCapacity - capacity;
        if (reduceCap > 0) {
            newCapacity = capacity;
            threshold = threshold - reduceCap;
        }
        final Node<K, V>[] oldTable = table;
        table = (Node<K, V>[]) new Node[newCapacity];
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] == null) {
                continue;
            }
            table[i] = oldTable[i];
            Node<K, V> node = table[i].next;
            // 更新对应的 index
            while (node != null) {
                node.index = i;
                node = node.hNext;
            }
        }
    }

    static class Node<K,V> {
        private K key;

        private V val;

        private int hash;

        // 水平 hash 链表
        private Node<K, V> hNext;

        // 双向链表前一个
        private Node<K, V> pre;

        // 双向链表后一个
        private Node<K, V> next;

        private int index;

        public Node(int index, K key, V val, int hash) {
            this.index = index;
            this.key = key;
            this.val = val;
            this.hash = hash;
        }
    }
}
