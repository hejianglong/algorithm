package string;

/**
 * @author hejianglong
 * @date 2019/7/17
 */
public class BM {
    private static final int SIZE = 256;

    public static void main(String[] args) {
        char[] source = new char[]{'a','b','c','a','c','a','b','c','b', 'c', 'b', 'a', 'c', 'a', 'b', 'c'};
        char[] pt = new char[]{'c','b','a', 'c', 'a', 'b', 'c'};
        int position = new BM().bm(source, source.length, pt, pt.length);
        System.out.println(position);
    }

    private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        for (int i = 0; i < m; i++) {
            suffix[i] = -1;
            prefix[i] = false;
        }
        for (int i = 0; i < m - 1; i++) {
            int j = i;
            int k = 0;
            while (j >= 0 && b[j] == b[m - 1 - k]) {
                --j;
                ++k;
                suffix[k] = j + 1;
            }
            if (j == -1) {
                prefix[k] = true;
            }
        }
    }

    private void generateBC(char[] b, int m, int[] bc) {
        for (int i = 0; i < SIZE; i++) {
            bc[i] = -1;
        }
        for (int i = 0; i < m; i++) {
            int assci = (int) b[i];
            bc[assci] = i;
        }
    }

    public int bm(char[] source, int n, char[] b, int m) {
        int[] bc = new int[SIZE];
        generateBC(b, m, bc);
        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];
        generateGS(b, m, suffix, prefix);
        int i = 0;
        while (i <= n - m) {
            int j;
            for (j = m - 1; j >= 0; j--) {
                if (source[i + j] != b[j]) {
                    break;
                }
            }
            if (j == -1) {
                return i;
            }
            int x = i + (j - bc[(int)source[i + j]]);
            int y = moveByGS(j, m , suffix, prefix);
            i = Math.max(x, y);
        }
        return -1;
    }

    private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
        int k = m - 1 - j;
        if (suffix[k] != -1) {
            return j - suffix[k] + 1;
        }
        for (int r = j + 2; r <= m - 1; r++) {
            if (prefix[m - r]) {
                return r;
            }
        }
        return m;
    }
}
