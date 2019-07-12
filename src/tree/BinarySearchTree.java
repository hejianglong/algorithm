package tree;

/**
 * 二叉搜索树
 * @author hejianglong
 * @date 2019/7/12
 */
public class BinarySearchTree {

    private Node tree;

    public void put(int val) {
        if (tree == null) {
            tree = new Node(val);
            return;
        }
        Node p = tree;
        Node newNode = new Node(val);
        while (p != null) {
            if (val < p.val) {
                if (p.left == null) {
                    p.left = newNode;
                    return;
                }
                p = p.left;
            } else {
                if (p.right == null) {
                    p.right = newNode;
                    return;
                }
                p = p.right;
            }
        }
    }

    public Node find(int val) {
        Node p = tree;
        while (p != null) {
            if (p.val < val) {
                p = p.right;
            } else if (p.val > val) {
                p = p.left;
            } else {
                return p;
            }
        }
        return null;
    }

    public void preOrder() {
        System.out.println("前序遍历结果:");
        preOrder(tree);
        System.out.println();
    }

    public void inOrder() {
        System.out.println("中序序遍历结果:");
        inOrder(tree);
        System.out.println();
    }

    public void afterOrder() {
        System.out.println("后序序遍历结果:");
        afterOrder(tree);
        System.out.println();
    }

    private void afterOrder(Node p) {
        if (p == null) {
            return;
        }
        afterOrder(p.left);
        afterOrder(p.right);
        System.out.print(p.val + " ");
    }

    private void inOrder(Node p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        System.out.print(p.val + " ");
        inOrder(p.right);
    }

    private void preOrder(Node p) {
        if (p == null) {
            return;
        }
        System.out.print(p.val + " ");
        preOrder(p.left);
        preOrder(p.right);
    }

    static class Node {
        private int val;

        private Node left;

        private Node right;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
