package tree;

/**
 * @author hejianglong
 * @date 2019/7/19
 */
public class Trie {

    private TrieNode root = new TrieNode('/');

    public static void main(String[] args) {
        Trie trie = new Trie();
        String hello = "hello";
        String hi = "hi";
        String her = "her";
        String how = "how";
        String see = "see";
        String so = "so";
        trie.insert(hello.toCharArray());
        trie.insert(hi.toCharArray());
        trie.insert(her.toCharArray());
        trie.insert(how.toCharArray());
        trie.insert(see.toCharArray());
        trie.insert(so.toCharArray());

        System.out.println(trie.find(her.toCharArray()));
    }

    public void insert(char[] text) {
        TrieNode p = root;
        for (int i = 0; i < text.length; i++) {
            int index = text[i] - 'a';
            if (p.children[index] == null) {
                TrieNode newNode = new TrieNode(text[i]);
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
    }

    public boolean find(char[] pattern) {
       TrieNode p = root;
       for (int i = 0; i < pattern.length; i++) {
           int index = pattern[i] - 'a';
           if (p.children[index] == null) {
               return false;
           }
           p = p.children[index];
       }
       if (p.isEndingChar) {
           return true;
       }
       return false;
    }

    public class TrieNode {
        public char data;

        public TrieNode[] children = new TrieNode[26];

        public boolean isEndingChar = false;

        public TrieNode(char data) {
            this.data = data;
        }
    }
}
