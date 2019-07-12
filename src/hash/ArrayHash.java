package hash;

/**
 * 线性探测解决 hash 冲突
 * @author hejianglong
 * @date 2019/7/12
 */
public class ArrayHash<K, V> {

    // 默认数组容量
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    // 装载因子
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Node<K, V>[] table;

    private int size;

    // 可以扩容槽位的值
    int threshold;

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public V put(K key, V val) {
        return putVal(hash(key), key, val);
    }

    public V get(K key) {
        return findVal(hash(key), key);
    }

    private V findVal(int hash, K key) {
        if (table == null || table.length == 0) {
            return null;
        }
        final int tableLength = table.length;
        int index = (tableLength - 1) & hash;
        Node<K, V> currentNode = table[index];
        if (currentNode != null && (currentNode.key == key || currentNode.key.equals(key))) {
            return currentNode.val;
        }
        int start = index + 1;
        int limit = tableLength;
        for (int i = start; i < limit; i++) {
            if (table[i] == null) {
                continue;
            }
            if (table[i].hash == hash && (table[i].key == key || table[i].key.equals(key))) { // 存在则更新
                return table[i].val;
            } else if (i == tableLength - 1) { // 如果向后遍历的所有数组空间都被占满，从 0 到 index 查找剩余空间
                limit = index;
                i = -1;
            }
        }
        return null;
    }

    private V putVal(int hash, K key, V val) {
        V result = null;
        if (table == null || table.length == 0) {
            resize();
        }
        final int tableLength = table.length;
        final int index = (tableLength - 1) & hash;
        Node<K, V> newNode = new Node<>(hash, key, val);
        Node<K, V> indexNode = table[index];
        // 不存则插入
        // 存在则更新
        if (indexNode == null) {
            table[index] = newNode;
        } else if (indexNode.hash == hash && (indexNode.key == key || indexNode.key.equals(key))) {
            result = table[index].val;
            table[index].val = val;
        } else {
            int start = index + 1;
            int limit = tableLength;
            for (int i = start; i < limit; i++) {
                if (table[i] == null) {
                    table[i] = newNode;
                    break;
                } else if (table[i].hash == hash && (table[i].key == key || table[i].key.equals(key))) { // 存在则更新
                    result = table[i].val;
                    table[i].val = val;
                    break;
                } else if (i == tableLength - 1) { // 如果向后遍历的所有数组空间都被占满，从 0 到 index 查找剩余空间
                    limit = index;
                    i = -1;
                }
            }
        }
        if (++size == threshold) {
            resize();
        }
        return result;
    }

    private void resize() {
        Node<K, V>[] oldTable = table;
        if (table == null || table.length == 0) {
            threshold = (int)(DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
            table = (Node<K, V>[]) new Node[DEFAULT_INITIAL_CAPACITY];
            return;
        }
        int oldCap = table.length;
        int newCap = 0;
        threshold = threshold << 1;
        newCap = oldCap << 1;
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[newCap];
        table = newTable;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] == null) {
                continue;
            }
            putVal(oldTable[i].hash, oldTable[i].key, oldTable[i].val);
        }
    }

    static class Node<K, V> {
        private int hash;
        private K key;
        private V val;
        public Node(int hash, K key, V val) {
            this.hash = hash;
            this.key = key;
            this.val = val;
        }
    }
}
