package exec.leetcode.june.four;

/**
 * <h1>#7 整数反转</h1>
 * <a href = 'https://leetcode-cn.com/problems/reverse-integer/'>题目</a>
 *
 * @author likeguo
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(Integer.parseInt("2344343"));
    }


    public int reverse(int x) {
        int t = x;
        int result = 0;
        while (t != 0) {
            // 此处数字为Integer.MAX_VALUE / 10;
            if ((x > 0 && result > 214748364) || (x < 0 && result < -214748364)) {
                return 0;
            }
            result = result * 10 + (t % 10);

            t = t / 10;
        }
        return (x > 0 && result > 0) || (x < 0 && result < 0) ? result : 0;
    }
}
