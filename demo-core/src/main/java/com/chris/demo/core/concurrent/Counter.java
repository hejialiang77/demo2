package com.chris.demo.core.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * TODO
 *
 * @ClassName AtomCounter
 * @Description TODO
 * @Author jlhe
 * @Date 2018/6/23 15:11
 * @Version 1.0
 */
public class Counter {

    AtomicInteger atomCounter = new AtomicInteger(0);
    int intCounter = 0;
    AtomicReference<Object> reference;

    public static void main(String[] args) throws InterruptedException {
        final Counter counter = new Counter();

        List<Thread> threads = new ArrayList<>(300);

        for (int i = 0; i < 300; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.cout();
                    counter.atomCount();
                }

            });
            threads.add(t);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(counter.intCounter);
        System.out.println(counter.atomCounter);



    }

    private void atomCount() {
        int i;
        do {
            i = atomCounter.get();
        } while (!atomCounter.compareAndSet(i, ++i));
    }

    private void cout() {
        intCounter++;
    }

}
