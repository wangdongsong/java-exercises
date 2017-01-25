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

        baseUsing();


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

