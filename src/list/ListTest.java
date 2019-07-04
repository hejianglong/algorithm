package list;

public class ListTest {

    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();

        for(int i = 0; i < 20; i++) {
            list.add(i);
        }
        list.printAll();
        //list.reversePrintAll();
        list.reverse();
        list.printAll();
    }
}
