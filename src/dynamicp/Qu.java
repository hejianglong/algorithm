package dynamicp;

/**
 * @author hejianglong
 * @date 2019/7/24
 */
public class Qu {

    private static int minDist = Integer.MIN_VALUE;

    public static void main(String[] args) {
        System.out.println(minMoney(10));
    }

    public static void minDistBT(int i, int j, int dist, int[][] w, int n) {
        if (i == n && j == n) {
            System.out.print(dist + " ");
            if (dist < minDist) {
                minDist = dist;
                return;
            }
        }
        if (i < n) {
            minDistBT(i + 1, j, dist + w[i][j], w, n);
        }
        if (j < n) {
            minDistBT(i, j + 1, dist +w[i][j], w, n);
        }
    }

    private static int minNum = Integer.MAX_VALUE;

    private static int[] value = {1,3,5};

    public static int minMoney(int w) {
        boolean[][] state = new boolean[3][16];
        state[0][0] = false;
        state[0][1] = true;
        state[0][3] = true;
        state[0][5] = true;
        if (w == 1 || w == 3 || w == 5) {
            return 1;
        }
        for (int i = 1; i < 3; i++) {
            for (int j = 0; j < 16; j++) {
                if (state[i - 1][j]) {
                    int v = j + value[0];
                    int v1 = j + value[1];
                    int v2 = j + value[2];
                    if (v == w || v1 == w || v2 == w) {
                        return i + 1;
                    }
                    state[i][v] = true;
                    state[i][v1] = true;
                    state[i][v2] = true;
                }
            }
        }
        return -1;
    }
}
