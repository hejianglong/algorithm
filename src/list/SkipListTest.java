package list;

public class SkipListTest {

    public static void main(String[] args) {
        SkipList<String> skipList = new SkipList<>();
        for (int i = 0; i < 100; i++) {
            skipList.put(i, "v:".concat(i + ""));
        }
        SkipList.Node node = skipList.search(100);
        System.out.println(node);
    }
}
