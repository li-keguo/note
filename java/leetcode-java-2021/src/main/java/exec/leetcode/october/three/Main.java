package exec.leetcode.october.three;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h1>#507. 完美数</h1>
 * <a href = 'https://leetcode-cn.com/problems/perfect-number/'>题目</a>
 *
 * @author likeguo
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(new Main().checkPerfectNumber(12));
        System.out.println(new FastMain().checkPerfectNumber(12));
    }

    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int sum = 1;
        for (int i = 2; i < Math.sqrt(num); i++) {
            if (num % i == 0) {
                sum += i;
                sum += num / i;
            }
        }
        return sum == num;
    }

    /**
     * 快速方案
     */
    static class FastMain extends Main {
        /**
         * 完美数
         */
        private final List<Integer> perfectNumber;

        public FastMain() {
            perfectNumber = Stream.of(6, 28, 496, 8128, 33550336)
                    .collect(Collectors.toList());
        }

        @Override
        public boolean checkPerfectNumber(int num) {
            return perfectNumber.contains(num);
        }
    }

}
