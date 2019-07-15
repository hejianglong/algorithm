package hash;

public class HashTest {

    public static void main(String[] args) {
        lruTest();
    }

    private static void listHashTest() {
        ListHash<Integer> hash = new ListHash<>();
        for (int i = 1; i < 300; i++) {
            hash.put("key:" + i, i);
        }
        Integer value = hash.get("key:13");
        System.out.println(value);
        int v = hash.put("key:13", 1293);
        System.out.println(v);
        System.out.println(hash.get("key:13"));
    }

    private static void arrayHashTest() {
        ArrayHash<String, Integer> hash = new ArrayHash<>();
        for (int i = 1; i < 300; i++) {
            hash.put("key:" + i, i);
        }
        Integer value = hash.get("key:13");
        System.out.println(value);
        int v = hash.put("key:13", 1293);
        System.out.println(v);
        System.out.println(hash.get("key:13"));
    }

    private static void lruTest() {
        LRU<String, Integer> lru = new LRU(10);
        for (int i = 0; i < 300; i++) {
            lru.put("key:" + i, i);
        }
        Integer value = lru.find("key:289");
        System.out.println(value);
    }
}
