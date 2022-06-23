package exec.leetcode.september.three;


/**
 * <h1>#344 时钟指针的夹角</h1>
 * <a href = 'https://leetcode-cn.com/problems/angle-between-hands-of-a-clock/'>题目</a>
 *
 * @author likeguo
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(new Main().angleClock(12, 12));
    }

    public double angleClock(int hour, int minutes) {
        // 小时走过的角度 - 分钟走过的角度
        double angle = Math.abs(30 * hour - 5.5 * minutes);
        return Math.min(angle, 360 - angle);
    }

}
