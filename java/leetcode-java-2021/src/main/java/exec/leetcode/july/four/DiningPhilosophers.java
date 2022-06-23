package exec.leetcode.july.four;

import java.util.concurrent.Semaphore;

/**
 * @author likeguo
 */
public class DiningPhilosophers {

    /**
     * 叉子
     */
    private static final Semaphore FORKS = new Semaphore(4);
    /**
     * 周转叉子
     */
    private static final Object TURNOVER_FORK = new Object();

    public DiningPhilosophers() {
    }

    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
      Thread.currentThread().setName(philosopher + "philosopher");
        // 没有叉子，等吧
        FORKS.acquire(philosopher);
        pickLeftFork.run();
        // 拿到周转叉子
        synchronized (TURNOVER_FORK) {
            pickRightFork.run();
            eat.run();
            putRightFork.run();
        }
        putLeftFork.run();
        FORKS.release(philosopher);

    }

}
