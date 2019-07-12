package hash;

/**
 * 散列表
 * 用链表解决 hash 冲突
 * 扩容槽位时候，从新 hash 搬移所有数据
 */
public class ListHash<T> {

    // 默认数组容量
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    // 装载因子
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Node<T>[] table;

    private int size;

    // 可以扩容槽位的值
    int threshold;

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public T get(String key) {
        return findVal(hash(key), key);
    }

    public T put(String key, T val) {
        return putVal(hash(key), key, val);
    }

    private T findVal(int hash, String key) {
        int length = table.length;
        int index = (length - 1) & hash;
        if (table == null || length == 0) {
            return null;
        }
        Node<T> currentNode = table[index];
        if (currentNode.hash == hash) {
            return currentNode.val;
        }
        currentNode = currentNode.next;
        while (currentNode != null) {
            if (currentNode.hash == hash && (currentNode.key.equals(key))) {
                return currentNode.val;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    private T putVal(int hash, String key, T val) {
        T result = null;
        if (table == null || table.length == 0) {
            resize();
        }
        int index = (table.length - 1) & hash;
        // 如果槽位没有值，直接插入
        Node<T> currentNode = table[index];
        Node<T> newNode = new Node<>(hash, key, val, null);
        if (currentNode == null) {
            table[index] = newNode;
        } else {
            Node<T> preNode = null;
            // 如果槽位有值，便利链表
            while (currentNode != null) {
                // 如果已经存在对应的 key
                if (currentNode.hash == hash && currentNode.key.equals(key)) {
                    result = currentNode.val;
                    currentNode.val = val;
                    break;
                }
                preNode = currentNode;
                currentNode = currentNode.next;
            }
            // 如果没有找到，则插入新值
            if (currentNode == null) {
                preNode.next = newNode;
            }
        }
        // 下一个长度达到了可扩充槽位的标准时, 进行扩容
        if (++size >= threshold) {
            resize();
        }
        return result;
    }

    /**
     * 暴力型一次性搬移数据从新 hash
     */
    private void resize() {
        Node<T>[] oldTable = table;
        int oldCap = table == null ? 0 : table.length;
        int newCap = 0;
        size = 0;
        int oldThr = threshold;
        if (oldCap == 0) {
            table = (Node<T>[]) new Node[DEFAULT_INITIAL_CAPACITY];
            threshold = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
            return;
        }
        threshold = oldThr << 1;
        newCap = oldCap << 1;
        Node<T>[] newTable = (Node<T>[]) new Node[newCap];
        table = newTable;
        // 数据搬移
        for (int i = 0; i < oldTable.length; i++) {
            Node<T> currentNode = oldTable[i];
            if (currentNode == null) {
                continue;
            }
            putVal(currentNode.hash, currentNode.key, currentNode.val);
        }
    }

    static class Node<T> {
        private int hash;
        String key;
        T val;
        Node<T> next;

        public Node(int hash, String key, T val, Node<T> next) {
            this.hash = hash;
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
}
