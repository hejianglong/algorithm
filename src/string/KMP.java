package string;

/**
 * @author hejianglong
 * @date 2019/7/19
 */
public class KMP {

    public static void main(String[] args) {
        char[] source = new char[]{'a','b','a','b','a','e','a','b','a','c'};
        char[] match = new char[]{'a','b','a','b','a','c'};
        char[] match2 = new char[]{'a','e','a'};
        int rs = kmp(source, source.length, match2, match2.length);
        System.out.println(rs);
    }

    public static int kmp(char[] a, int n, char[] b, int m) {
        int[] next = getNexts(b, m);
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (j > 0 && a[i] != b[j]) {
                j = next[j - 1] + 1;
            }
            if (a[i] == b[j]) {
                ++j;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

    private static int[] getNexts(char[] b, int m) {
        int[] next = new int[m];
        for (int i = 0; i < m; i++) {
            next[i] = -1;
        }
        int k = -1;
        for (int i = 1; i < m; i++) {
            while (k != -1 && b[k + 1] != b[i]) {
                k = next[k];
            }
            if (b[k + 1] == b[i]) {
                ++k;
            }
            next[i] = k;
        }
        return next;
    }
}
