package list;

public class ListTest {

    public static void main(String[] args) {
        singleLinkedListTest2();
    }

    private static void singleLinkedListTest2() {
        LinkedListAlgo.Node node1 = LinkedListAlgo.initTestList(new int[]{1,3,5,7,9});
        LinkedListAlgo.Node node2 = LinkedListAlgo.initTestList(new int[]{2,4,6,8,10});
        LinkedListAlgo.Node merageNode = LinkedListAlgo.mergeSortedLists(node1, node2);
        LinkedListAlgo.printAll(merageNode);
    }

}
