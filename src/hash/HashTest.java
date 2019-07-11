package hash;

public class HashTest {

    public static void main(String[] args) {
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

}
