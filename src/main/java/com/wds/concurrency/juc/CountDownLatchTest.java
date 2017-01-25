package com.wds.concurrency.juc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CountDownLatch;

/**
 * Created by wds on 2015/10/25.
 */
public class CountDownLatchTest {
    private static final Logger LOGGER = LogManager.getLogger(CountDownLatchTest.class);

    public static void main(String[] args) throws InterruptedException {

        //基本用法
        //baseUsing();

        baseUsing(2);

    }

    private static void baseUsing(int flag) {
        CountDownLatch cdl = new CountDownLatch(1);

        new Thread(() -> {
            try {
                LOGGER.info("CountDownLatch is waiting, and the baseline is " + System.currentTimeMillis());
                cdl.await();
                LOGGER.info("CountDownLatch contiue execute, and the endline is " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            }

        }).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }

        cdl.countDown();
    }


    /**
     * CountDownLatch基本用法
     * @throws InterruptedException
     */
    private static void baseUsing() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        new Thread(() -> run(1, countDownLatch)).start();
        new Thread(() -> run(2, countDownLatch)).start();
        new Thread(() -> run(3, countDownLatch)).start();

        countDownLatch.await();

        LOGGER.info("Main is end");
    }

    private static void run(int i, CountDownLatch countDownLatch) {
        LOGGER.info("Worker-" + i + "is running");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info("Worker-" + i + "is end");
        countDownLatch.countDown();
    }
}

