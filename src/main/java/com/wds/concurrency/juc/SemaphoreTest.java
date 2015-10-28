package com.wds.concurrency.juc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;

/**
 * Created by wds on 2015/10/25.
 */
public class SemaphoreTest {
    private final static Logger LOGGER = LogManager.getLogger(SemaphoreTest.class);

    public static void main(String[] args) {

        final Semaphore semaphore = new Semaphore(3);

        for (Integer i = 0; i < 5; i++) {
            final Integer finalI = i;
            new Thread(() -> run(finalI, semaphore)).start();
        }

    }

    public static void run(int index, Semaphore semaphore) {
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
