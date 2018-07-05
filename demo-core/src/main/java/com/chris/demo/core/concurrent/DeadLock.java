package com.chris.demo.core.concurrent;

/**
 * TODO
 *
 * @ClassName DeadLock
 * @Description TODO
 * @Author hejl002
 * @Date 2018/6/21 21:27
 * @Version 1.0
 */
public class DeadLock {

    static String A = "A";
    static String B = "B";


    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {

            synchronized (A) {
                System.out.println("A");
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                }
            }
        });

        Thread t2 = new Thread(() -> {

            synchronized (B) {
                System.out.println(B);
                synchronized (A) {
                }
            }
        });

        t1.start();
        t2.start();
    }
}
