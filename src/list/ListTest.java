package list;

public class ListTest {

    public static void main(String[] args) {
        singleLinkedListTest2();
    }

    private static void singleLinkedListTest2() {
        LinkedListAlgo.Node node1 = LinkedListAlgo.initTestList(new int[]{2, 4, 5, 7, 9});
        //LinkedListAlgo.Node node2 = LinkedListAlgo.initTestList(new int[]{1,3,5,9});
        //LinkedListAlgo.Node merageNode = LinkedListAlgo.mergeSortedLists(node1, node2);
        //LinkedListAlgo.printAll(merageNode);
        //LinkedListAlgo.Node delNodel = LinkedListAlgo.deleteNode(node1, 1);
        //LinkedListAlgo.Node delNodel = LinkedListAlgo.deleteLastNode(node1, 2);
        LinkedListAlgo.Node middleNode = LinkedListAlgo.findMiddleNode(node1);
        LinkedListAlgo.printAll(middleNode);
    }

}
