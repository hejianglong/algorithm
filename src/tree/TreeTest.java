package tree;

/**
 * @author hejianglong
 * @date 2019/7/12
 */
public class TreeTest {

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.put(33);
        tree.put(16);
        tree.put(50);
        tree.put(13);
        tree.put(18);
        tree.put(15);
        tree.put(17);
        tree.put(25);
        tree.put(19);
        tree.put(27);
        tree.put(34);
        tree.put(58);
        tree.put(51);
        tree.put(66);
        tree.put(55);
        tree.preOrder();
        tree.inOrder();
        tree.afterOrder();
        tree.delete(18);
        tree.inOrder();
        tree.delete(13);
        tree.inOrder();
        tree.delete(55);
        tree.inOrder();
        System.out.println("最大值:" + tree.findMaxValue());
        System.out.println("最小值:" + tree.findMinValue());
    }
}
