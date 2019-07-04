package list;

public class ListTest {

    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();

        for(int i = 0; i < 20; i++) {
            list.add(i);
        }
        list.printAll();
        // 翻转输出
        list.reversePrintAll();
        // 翻转
        list.reverse();
        list.printAll();
        // 翻转输出
        list.reversePrintAll();
    }

}
