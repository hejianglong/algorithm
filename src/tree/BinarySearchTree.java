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

    public void delete(int val) {
        Node p = tree;
        Node pre = null;
        while (p != null) {
            if (p.val < val) {
                pre = p;
                p = p.right;
            } else if (p.val > val) {
                pre = p;
                p = p.left;
            } else {
                // 删除逻辑
                // 如果处于叶子结点，直接删除
                if (p.left == null && p.right == null) {
                    // 如果大于上一个结点的值，证明需要删除的是右节点
                    if (pre.val < val) {
                        pre.right = null;
                        return;
                    }
                    pre.left = null;
                    return;
                } else if (p.left == null || p.right == null) { // 如果删除结点下面只有单个结点
                    if (pre.val < val) {
                        pre.right = (p.left == null ? p.right : p.left);
                        return;
                    }
                    pre.left = (p.left == null ? p.right : p.left);
                    return;
                } else { // 如果删除结点下面有多个结点
                    // 找到删除结点右子树最小结点，替换掉删除的结点
                    Node replaceNode = p.right;
                    Node preReplaceNode = null;
                    while (replaceNode.left != null) {
                        preReplaceNode = replaceNode;
                        replaceNode = replaceNode.left;
                    }
                    // 右子树不止有一个结点，存在大于等于 1个左结点
                    if (replaceNode.val < p.right.val) {
                        preReplaceNode.left = null;
                        replaceNode.right = p.right;
                    }
                    replaceNode.left = p.left;
                    // 待删除的结点是父节点的右结点
                    if (pre.val < val) {
                        pre.right = replaceNode;
                        return;
                    }
                    pre.left = replaceNode;
                    return;
                }
            }
        }
    }

    public Node findMaxValue() {
        Node p = tree;
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

    public Node findMinValue() {
        Node p = tree;
        while (p.left != null) {
            p = p.left;
        }
        return p;
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
