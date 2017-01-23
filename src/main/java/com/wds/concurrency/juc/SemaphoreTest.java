package com.wds.concurrency.juc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Created by wds on 2015/10/25.
 */
public class SemaphoreTest {
    private final static Logger LOGGER = LogManager.getLogger(SemaphoreTest.class);

    public static void main(String[] args) {

        //简单使用
        //sampleTest();

        //初始值
        //testInitValue();

        //多次release时，许可自动增加
        //许可数量清零
        //testReleasePermits();

        //queue
        testQueue();


    }

    private static void testQueue() {

        Semaphore semaphore = new Semaphore(1);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {

                    semaphore.acquire();
                    LOGGER.info(Thread.currentThread().getName() + ": " + semaphore.getQueueLength() + " threads waiting!");

                    LOGGER.info("Is thread waiting for semaphore:" + semaphore.hasQueuedThreads());
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage(), e);
                } finally {
                    semaphore.release();
                }

            }).start();

        }

    }

    /**
     * 释放多次时，可以改变许可数量
     */
    private static void testReleasePermits() {
        Semaphore semaphore = new Semaphore(10);
        try {
            semaphore.acquire();
            semaphore.acquire();

            LOGGER.info(semaphore.availablePermits());
            Thread.sleep(1000);

            //释放多次之后可以改变许可数量
            semaphore.release();
            semaphore.release();
            semaphore.release();

            LOGGER.info(semaphore.availablePermits());

            semaphore.release(4);

            LOGGER.info(semaphore.availablePermits());

            semaphore.acquire(15);

            LOGGER.info(semaphore.availablePermits());

            semaphore.release(15);

            //返回可用的许可数并将可用许可清零
            LOGGER.info(semaphore.drainPermits());
            LOGGER.info(semaphore.availablePermits());

        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    /**
     * 同时获取多个许可
     */
    private static void testInitValue() {
        Semaphore semaphore = new Semaphore(10);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> run(semaphore)).start();
        }
    }

    /**
     * 同时获取多个许可
     * @param semaphore
     */
    private static void run(Semaphore semaphore) {
        try {
            semaphore.acquire(2);

            LOGGER.info(Thread.currentThread().getName());

            Thread.sleep(new Random(1).nextInt(1000));

            semaphore.release(2);

        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * 获取单个许可测试
     */
    private static void sampleTest() {
        final Semaphore semaphore = new Semaphore(3);

        for (Integer i = 0; i < 5; i++) {
            final Integer finalI = i;
            new Thread(() -> run(finalI, semaphore)).start();
        }
    }

    private static void run(int index, Semaphore semaphore) {
        LOGGER.info("User index " + index + " is connected!");
        try {
            Thread.sleep(3000L);
            semaphore.acquire();

            LOGGER.info("User index " + index + " is running");

            semaphore.release();

            LOGGER.info("User index " + index + " is end");
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
