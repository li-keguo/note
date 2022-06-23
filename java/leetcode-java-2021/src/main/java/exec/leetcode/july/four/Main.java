package exec.leetcode.july.four;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liekguo
 */
public class Main {
    /**
     * 测试次数
     */
    private static final int N = 3;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(() -> extracted(1));
        executorService.submit(() -> extracted(2));
        executorService.submit(() -> extracted(3));
        executorService.submit(() -> extracted(4));
        executorService.submit(() -> extracted(5));
        executorService.shutdown();
    }

    private static void extracted(int philosopherId) {
        DiningPhilosophers philosophers = new DiningPhilosophers2();
        try {
            for (int i = 0; i < N; i++) {
                philosophers.wantsToEat(philosopherId,
                        () -> System.out.println(Thread.currentThread().getName() + ":pickLeftFork(选择左叉)"),
                        () -> System.out.println(Thread.currentThread().getName() + ":pickRightFork(选择右叉)"),
                        () -> System.out.println(Thread.currentThread().getName() + ":eat（吃）"),
                        () -> System.out.println(Thread.currentThread().getName() + ":putLeftFork（放左叉）"),
                        () -> System.out.println(Thread.currentThread().getName() + ":putRightFork（放右叉）")
                );
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
