import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author hejianglong
 * @date 2019/7/23
 */
public class dynamicp {

    public static void main(String[] args) {
       /* int[] weight = new int[]{2,2,4,6,3};

        int rs = knapsack2(weight, weight.length, 9);
        int rs2 = KnaspackPlus.knaspack(KnaspackPlus.weights, KnaspackPlus.prices, KnaspackPlus.n, KnaspackPlus.w);
        System.out.println(rs);
        System.out.println(rs2);*/
        int[][] data = PascalsTriangle.getData(5);
        System.out.println("构造的杨辉三角如下");
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] == -1) {
                    continue;
                }
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("动态规划生成树如下");
        int min = PascalsTriangle.path(data, 5);
        System.out.println("杨辉三角到达低端最短结点值合为: " + min);

        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        List<Integer> l3 = new ArrayList<>();
        l1.add(-1);

        l2.add(2);
        l2.add(3);

        l3.add(1);
        l3.add(-1);
        l3.add(-3);
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);

/*        List<Integer> l3 = new ArrayList<>();
        l3.add(2);
        List<Integer> l4 = new ArrayList<>();
        l4.add(3);
        l4.add(4);
        List<Integer> l5 = new ArrayList<>();
        l5.add(6);
        l5.add(5);
        l5.add(7);
        List<Integer> l6 = new ArrayList<>();
        l6.add(4);
        l6.add(1);
        l6.add(8);
        l6.add(3);
        lists.add(l3);
        lists.add(l4);
        lists.add(l5);
        lists.add(l6);*/
        int min2 = PascalsTriangle.minimumTotal(lists);
        System.out.println(min2);
    }

    public static int knapsack(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w + 1];
        states[0][0]=true;
        states[0][weight[0]]=true;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= w; j++) {
                if (states[i - 1][j]) {
                    states[i][j] = states[i-1][j];
                }
            }
            for (int j = 0; j + weight[i] <= w; j++) {
                if (states[i-1][j]) {
                    states[i][j + weight[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; i--) {
            if (states[n-1][i]) {
                return i;
            }
        }
        return 0;
    }

    public static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w+1];
        states[0] = true;
        states[items[0]] = true;
        for (int i = 1; i < n; i++) {
            for (int j = w - items[i]; j >= 0; j--) {
                if (states[j]) {
                    states[j + items[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; i--) {
            if (states[i]) {
                return i;
            }
        }
        return 0;
    }
}
class KnaspackPlus {

    static int[] weights = {2,2,4,6,3};
    static int[] prices = {3,4,8,9,6};
    static int n = 5;
    static int w = 9;

    public static int knaspack(int[] weight, int[] value, int n, int w) {
        int[][] states = new int[n][w+1];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= w; j++) {
                if (states[i - 1][j] >= 0) {
                    states[i][j] = states[i - 1][j];
                }
            }
            for (int j = 0; j <= w - weight[i]; j++) {
                if (states[i - 1][j] >= 0) {
                    int v = states[i - 1][j] + value[i];
                    if (v > states[i][j + weight[i]]) {
                        states[i][j + weight[i]] = v;
                    }
                }
            }
        }
        for (int i = 0; i < states.length; i++) {
            for (int j = 0; j < states[i].length; j++) {
                System.out.print(states[i][j]+ " ");
            }
            System.out.println();
        }
        int max = -1;
        for (int j = 0; j <= w; j++) {
            if (states[n-1][j] > max) {
                max = states[n-1][j];
            }
        }
        return max;
    }
}
class Tabao {

    /**
     *
     * @param items 商品的价格
     * @param n 商品个数
     * @param w 满减条件
     */
    public static void double11advance(int[] items, int n, int w) {
        boolean[][] states = new boolean[n][3*w+1]; // 超过3倍价格就没有撸羊毛的价值了
        states[0][0] = true;
        states[0][items[0]] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= 3 * w; j++) {
                if (states[i - 1][j]) {
                    states[i][j] = states[i - 1][j];
                }
            }
            for (int j = 0; j <= 3 * w - items[i]; j++) {
                if (states[i - 1][j]) {
                    states[i][j + items[i]] = true;
                }
            }
        }

        int j;
        // 输出结果大于等于 W 的最小值
        for (j = w; j < 3 * w + 1; j++) {
            if (states[n - 1][j]) {
                break;
            }
        }
        // 无解
        if (j == -1) {
            return;
        }
        for (int i = n - 1; i >= 1; i--) {
            if (j - items[i] >= 0 && states[i - 1][j - items[i]]) {
                System.out.println(items[i] + " ");
                j = j - items[i];
            }
        }
        if (j != 0) {
            System.out.println(items[0]);
        }
    }
}
class PascalsTriangle {

    /**
     * @param n 几层
     * @return
     */
    public static int[][] getData(int n) {
        Random random = new Random();
        int[][] data = new int[n][n];
        // 初始化 -1 之后标示没有值
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = -1;
            }
        }
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < i + 1; j++) {
                data[i][j] = random.nextInt(10);
            }
        }
        return data;
    }
    public static int path(int[][] data, int n) {
        int[][] state = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                state[i][j] = -1;
            }
        }
        state[0][0] = data[0][0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (state[i- 1][j] != -1) {
                    state[i][j] = state[i - 1][j] + data[i][j];
                    state[i][j+1] = state[i - 1][j] + data[i][j + 1];
                }
            }
        }
        int min = Integer.MAX_VALUE;
        print(state);
        for (int i = n - 1; i >= 0; i--) {
            if (state[n - 1][i] == -1) {
                continue;
            }
            if (state[n - 1][i] < min) {
                min = state[n - 1][i];
            }
        }
        return min;
    }

    private static void print(int[][] states) {
        int length = states.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (states[i][j] == -1) {
                    continue;
                }
                System.out.print(states[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[][] state = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                state[i][j] = Integer.MIN_VALUE;
            }
        }
        state[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (state[i - 1][j] != Integer.MIN_VALUE) {
                    int left = state[i - 1][j] + triangle.get(i).get(j);
                    int right = state[i - 1][j] + triangle.get(i).get(j + 1);
                    if (state[i][j] == Integer.MIN_VALUE) {
                        state[i][j] = left;
                    }
                    if (state[i][j+1] == Integer.MIN_VALUE) {
                        state[i][j + 1] = right;
                    }
                    if (left < state[i][j]) {
                        state[i][j] = left;
                    }
                    if (right < state[i][j + 1]) {
                        state[i][j + 1] = right;
                    }
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            if (state[size - 1][i] == Integer.MIN_VALUE) {
                continue;
            }
            if (state[size - 1][i] < min) {
                min = state[size - 1][i];
            }
        }
        return min;
    }
}