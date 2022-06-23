package exec.leetcode.june.first;

/**
 * <h1>#45 跳跃游戏</h1>
 * <a href = 'https://leetcode-cn.com/problems/jump-game-ii/'>题目</a>
 *
 * @author mlamp
 */
public class Main {

    public static void main(String[] args) {
        int[] arr = {2, 3, 0, 1, 4, 4};
        int jump = jump(arr);
        System.out.println(jump);
    }

    /**
     * @param arr arr
     * @return step
     */
    public static int jump(int[] arr) {
        int flag = 0;
        int maxPosition = 0;
        int countStep = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            maxPosition = Math.max(maxPosition, arr[i] + i);
            // 移动到视野最大处
            if (i == flag) {
                flag = maxPosition;
                countStep++;
            }
            if (flag >= arr.length) {
                // 视野超过了最终点，
                break;
            }
        }
        return countStep;
    }
}
