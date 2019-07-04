package list;

/**
 *  LRU 淘汰算法
 */
public class LRU {

    public static void main(String[] args) {
        LRUSingleLinkedList lru = new LRUSingleLinkedList(10);
        for(int i = 0; i < 10; i++) {
            lru.add(i);
        }
        lru.print();
        lru.add(8);
        lru.print();
        lru.add(8);
        lru.print();
        lru.add(9);
        lru.print();
    }
}
