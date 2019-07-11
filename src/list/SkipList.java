package list;

import java.util.Random;

/**
 * 跳表实现
 */
public class SkipList<T> {

    private Node head, tail;

    private Random random = new Random();

    // 向上提升层级的概率
    private final static double PROBABILITY = 0.5;

    private int levelCount = 0;

    // 结点总数
    private int nodeSize = 0;

    public SkipList() {
        clear();
    }

    public void put(int key, T value) {
        Node p = findNode(key);
        // 如果对应的 key 已经存在则更新
        if (p.key == key) {
            p.value = value;
            return;
        }
        Node<T> q = new Node<>(key, value);
        // 将新的节点插入到最底层节最接近其值点的后面
        backLink(p, q);
        int currentLevelCount = 0;
        // 增加层级索引
        while (random.nextDouble() < PROBABILITY) {
            if (currentLevelCount >= levelCount) {
                Node<T> p1 = new Node(Node.HEAD_KEY, null);
                Node<T> p2 = new Node(Node.TAL_KEY, null);
                verticalLink(p1, head);
                verticalLink(p2, tail);
                horizontalLink(p1, p2);
                head = p1;
                tail = p2;
                levelCount++;
            }
            // 取出左侧最近的上一个层级结点
            while (p.up == null){
                p = p.left;
            }
            p = p.up;
            // 复制一个节点
            Node<T> newNode = new Node<>(key, null);
            // 跟在节点左侧
            backLink(p, newNode);
            // 上下相连
            verticalLink(newNode, q);
            q = newNode;
            currentLevelCount++;
        }
        nodeSize++;
    }

    public Node<T> search(int key) {
        Node<T> node = findNode(key);
        return node.key == key ? node : null;
    }

    private void clear() {
        head = new Node(Node.HEAD_KEY, null);
        tail = new Node(Node.TAL_KEY, null);
        horizontalLink(head, tail);
        levelCount = 0;
        nodeSize = 0;
    }

    private void horizontalLink(Node<T> p, Node<T> q) {
        p.right = q;
        q.left = p;
    }

    private void verticalLink(Node<T> p, Node<T> q) {
        p.down = q;
        q.up = p;
    }

    /**
     * 将 P 节点连接在 newNode 节点后面
     * @param p
     * @param newNode
     */
    private void backLink(Node<T> p, Node<T> newNode) {
        newNode.left = p;
        newNode.right = p.right;
        p.right.left = newNode;
        p.right = newNode;
    }

    /**
     * 查找 key 所处最底层区间节点
     * 结果 Node key 值不一定等于 key
     * @param key
     * @return
     */
    private Node findNode(int key) {
        Node p = head;
        for(;;) {
            while (p.right.key != Node.TAL_KEY && p.right.key <= key) {
                p = p.right;
            }
            if (p.down != null) {
                p = p.down;
            } else {
                break;
            }
        }
        return p;
    }

    class Node<T> {

        private int key;

        private T value;

        private Node<T> up, down, left, right;

        private final static int HEAD_KEY = Integer.MIN_VALUE;

        private final static int TAL_KEY = Integer.MAX_VALUE;

        public Node(int key, T value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
