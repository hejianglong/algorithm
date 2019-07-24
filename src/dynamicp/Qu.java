package dynamicp;

/**
 * @author hejianglong
 * @date 2019/7/24
 */
public class Qu {

    private static int minDist = Integer.MIN_VALUE;

    public static void main(String[] args) {
        int[][] w = new int[4][4];
        w[0][0] = 1;
        w[0][1] = 3;
        w[0][2] = 5;
        w[0][3] = 9;

        w[1][0] = 2;
        w[1][1] = 1;
        w[1][2] = 3;
        w[1][3] = 4;

        w[2][0] = 5;
        w[2][1] = 2;
        w[2][2] = 6;
        w[2][3] = 7;

        w[3][0] = 6;
        w[3][1] = 8;
        w[3][2] = 4;
        w[3][3] = 3;
        minDistBT(0,0,0,w,3);
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

    public static void lessMoney() {

    }
}
