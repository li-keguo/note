package com.test.multithreading;

/**
 * 共享内存通讯
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class SharedMemoryCommunication {
    static volatile int i = 1;
    
    /**
     * 三个线程， A、B、C。A的功能为打印A，B的功能为打印B，C的功能为打印C。要求循环输出
     * A
     * B
     * C
     * A
     * B
     * C
     *
     * @param args args
     */
    public static void main(String[] args) {
        
        final Thread threadA = new Thread(() -> {
            while (true) {
                if (i % 3 == 1) {
                    System.out.println("A");
                    i++;
                }
                sleep();
            }
            
        });
        final Thread threadB = new Thread(() -> {
            while (true) {
                if (i % 3 == 2) {
                    System.out.println("B");
                    i++;
                }
                sleep();
            }
        });
        final Thread threadC = new Thread(() -> {
            while (true) {
                if (i % 3 == 0) {
                    System.out.println("C");
                    i++;
                }
                sleep();
            }
        });
        threadA.start();
        threadB.start();
        threadC.start();
    }
    
    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
