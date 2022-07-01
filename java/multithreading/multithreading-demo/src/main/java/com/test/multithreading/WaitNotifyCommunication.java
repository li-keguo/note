package com.test.multithreading;

/**
 * 等待-通知通讯
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class WaitNotifyCommunication {
    
    static Object lock1 = new Object();
    
    static Object lock2 = new Object();
    
    static Object lock3 = new Object();
    
    /**
     * 三个线程， A、B、C。A的功能为打印A，B的功能为打印B，C的功能为打印C。要求循环输出
     * A
     * B
     * C
     * A
     * B
     * C
     *
     * @param args
     */
    public static void main(String[] args) {
        
        final Thread threadA = new Thread(() -> {
            while (true) {
                synchronized (lock1) {
                    System.out.println("A");
                    sleep();
                    synchronized (lock2) {
                        lock2.notify();
                    }
                    waitL(lock1);
                }
            }
            
        });
        final Thread threadB = new Thread(() -> {
            while (true) {
                synchronized (lock2) {
                    waitL(lock2);
                    System.out.println("B");
                    sleep();
                    synchronized (lock3) {
                        lock3.notify();
                    }
                }
                
            }
        });
        final Thread threadC = new Thread(() -> {
            while (true) {
                synchronized (lock3) {
                    waitL(lock3);
                    System.out.println("C");
                    sleep();
                    synchronized (lock1) {
                        lock1.notify();
                    }
                }
            }
        });
        threadA.start();
        threadB.start();
        threadC.start();
    }
    
    private static void waitL(Object lock) {
        try {
            lock.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
