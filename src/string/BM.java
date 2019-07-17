package string;

/**
 * @author hejianglong
 * @date 2019/7/17
 */
public class BM {
    private static final int SIZE = 256;

    public static void main(String[] args) {
        char[] source = new char[]{'a','b','c','a','c','a','b','d','c'};
        char[] pt = new char[]{'a','b','d'};
        int position = new BM().bm(source, source.length, pt, pt.length);
        System.out.println(position);
    }

    private void generateBC(char[] b, int m, int[] bc) {
        for (int i = 0; i < SIZE; i++) {
            bc[i] = -1;
        }
        for (int i = 0; i < m; i++) {
            int assci = (int)b[i];
            bc[assci] = i;
        }
    }

    public int bm(char[] source, int n, char[] b, int m) {
        int[] bc = new int[SIZE];
        generateBC(b, m, bc);
        int i = 0;
        while (i <= n - m) {
            int j;
            for (j = m - 1; j >= 0; j--) {
                if (source[i + j] != b[j]) {
                    break;
                }
            }
            if (j < 0) {
                return i;
            }
            i = i + (j - bc[(int)source[i + j]]);
        }
        return -1;
    }
}
