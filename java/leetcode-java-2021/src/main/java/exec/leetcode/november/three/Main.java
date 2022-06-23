package exec.leetcode.november.three;


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <h1>#1834. 单线程 CPU</h1>
 * <a href = 'https://leetcode-cn.com/problems/single-threaded-cpu/'>题目</a>
 *
 * @author likeguo
 */
public class Main {

    public static void main(String[] args) {
        final Main main = new Main();
        print(main.getOrder(new int[][]{{1, 2}, {2, 4}, {3, 2}, {4, 1}}));
        print(main.getOrder(new int[][]{{7, 10}, {7, 12}, {7, 5}, {7, 4}, {7, 2}}));
    }

    private static void print(int[] order) {
        System.out.println(Arrays.toString(order));
    }

    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[][] newTasks = new int[n][3];

        for (int i = 0; i < n; i++) {
            newTasks[i][0] = tasks[i][0];
            newTasks[i][1] = tasks[i][1];
            newTasks[i][2] = i;
        }

        Arrays.sort(newTasks, Comparator.comparingInt(o -> o[0]));
        PriorityQueue<int[]> taskQueue = new PriorityQueue<>((o1, o2) -> o1[1] != o2[1] ? o1[1] - o2[1] : o1[2] - o2[2]);

        int time = 0;
        int resIdx = 0;
        int enqIdx = 0;
        int[] res = new int[n];
        while (resIdx < n) {
            while (enqIdx < n && newTasks[enqIdx][0] <= time) {
                taskQueue.offer(newTasks[enqIdx++]);
            }
            if (taskQueue.isEmpty()) {
                time = newTasks[enqIdx][0];
                continue;
            }
            int[] nextJob = taskQueue.poll();
            res[resIdx++] = nextJob[2];
            time += nextJob[1];
        }
        return res;
    }


}
