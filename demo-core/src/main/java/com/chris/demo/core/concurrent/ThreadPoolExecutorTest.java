package com.chris.demo.core.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorTest {
    public static void main(String[] args) throws InterruptedException {

        // 线程安全的计数器
        AtomicInteger totalRows = new AtomicInteger(0);
        int size = 20;
        // 创建线程池，其中核心线程10，也是我期望的最大并发数，最大线程数和队列大小都为30，即我的总任务数
        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(size);
        //或者ExecutorService executor = Executors.newFixedThreadPool(corePoolSize);

        // 初始化CountDownLatch，大小为30
        CountDownLatch countDownLatch = new CountDownLatch(size);

        // 模拟遍历参数集合
        for (int i = 0; i < size; i++) {
            // 往线程池提交任务
            threadPoolExecutor.execute(() -> {
                int times = 0;
                // 模拟数据拉取过程可能需要分页
                while (true) {
                    // 模拟每个任务需要分页5次
                    if (times >= 5) {
                        break;
                    }
                    times++;

                    // 模拟计数
                    totalRows.incrementAndGet();
                    try {
                        // 模拟耗时
                        Thread.sleep(Long.valueOf(String.valueOf(new Double(Math.random() * 1000).intValue())));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 子线程完成，countDownLatch执行countDown
                countDownLatch.countDown();
            });
            // 打印线程池运行状态
//            System.out.println("线程池中线程数目：" + threadPoolExecutor.getPoolSize() + "，队列中等待执行的任务数目：" +
//                    threadPoolExecutor.getQueue().size() + "，已执行结束的任务数目：" + threadPoolExecutor.getCompletedTaskCount());
        }
        // 标记多线程关闭，但不会立马关闭
        threadPoolExecutor.shutdown();

        // 阻塞当前线程，知道所有子线程都执行countDown方法才会继续执行
        countDownLatch.await();

        // 打印线程池运行状态
//        System.out.println("线程池中线程数目：" + threadPoolExecutor.getPoolSize() + "，队列中等待执行的任务数目：" +
//                threadPoolExecutor.getQueue().size() + "，已执行结束的任务数目：" + threadPoolExecutor.getCompletedTaskCount());

        // 打印计数
        System.out.println("结束：" + totalRows.get());
    }
}