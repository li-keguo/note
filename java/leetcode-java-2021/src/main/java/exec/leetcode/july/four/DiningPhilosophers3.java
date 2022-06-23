package exec.leetcode.july.four;

import java.util.concurrent.Semaphore;

/**
 * @author likeguo
 */
public class DiningPhilosophers3 extends DiningPhilosophers{

    /**
     * 五把叉子
     */
    private static final Object[] FORK_LOCKS = {new Object(), new Object(), new Object(), new Object(), new Object()};

    /**
     * 限流4人同时进行
     */
    private static final Semaphore FORKS = new Semaphore(4);

    public DiningPhilosophers3() {

    }


    @Override
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        int leftForkLockIndex = (philosopher + 1) % 5;
        int rightForkLockInde = philosopher % 5;
        Thread.currentThread().setName(philosopher + "philosopher");
        // 没有叉子，等吧
        FORKS.acquire(philosopher);
        // 拿左边叉子
        synchronized (FORK_LOCKS[leftForkLockIndex]) {
            pickLeftFork.run();
            // 拿右边叉子
            synchronized (FORK_LOCKS[rightForkLockInde]) {
                pickRightFork.run();
                eat.run();
                // 放右边叉子
                putRightFork.run();
            }
            // 放左边叉子
            putLeftFork.run();
        }
        FORKS.release(philosopher);
    }

}
