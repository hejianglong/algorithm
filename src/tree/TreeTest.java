package tree;

/**
 * @author hejianglong
 * @date 2019/7/12
 */
public class TreeTest {

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.put(6);
        tree.put(4);
        tree.put(8);
        tree.put(2);
        tree.put(5);
        tree.put(1);
        tree.put(3);
        tree.put(7);
        tree.put(9);
        System.out.println(tree.find(4));
        tree.preOrder();
        tree.inOrder();
        tree.afterOrder();
    }
}
