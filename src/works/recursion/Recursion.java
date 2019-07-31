package works.recursion;

/**
 * 现斐波那契数列求值
 * 求阶乘 n!
 * 实现一组数据集合的全排列
 * @author hejianglong
 * @date 2019/7/30
 */
public class Recursion {

    public static void main(String[] args) {
        System.out.println("斐波那契数列第5个值为:  " + fibonacci(5));
        System.out.println("阶乘: " + factorial(5));
    }

    public static int fibonacci(int n) {
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static void qpl(int[] arr, int n) {

    }
}
