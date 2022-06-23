package exec.leetcode.july.four;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author likeguo
 */
public class DiningPhilosophers2 extends DiningPhilosophers {
    /**
     * 五把叉子
     */
    private static final ReentrantLock[] FORK_LOCKS = {new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock()};

    /**
     * 限流4人同时进行
     */
    private static final Semaphore FORKS = new Semaphore(4);

    public DiningPhilosophers2() {
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
        FORK_LOCKS[leftForkLockIndex].lock();
        pickLeftFork.run();
        // 拿右边叉子
        FORK_LOCKS[rightForkLockInde].lock();
        pickRightFork.run();
        eat.run();
        // 放右边叉子
        FORK_LOCKS[rightForkLockInde].unlock();
        putRightFork.run();
        // 放左边叉子
        FORK_LOCKS[leftForkLockIndex].unlock();
        putLeftFork.run();
        FORKS.release(philosopher);
    }

}
