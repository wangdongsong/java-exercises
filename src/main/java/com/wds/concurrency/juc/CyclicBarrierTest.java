package com.wds.concurrency.juc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * Created by wds on 2015/10/25.
 */
public class CyclicBarrierTest {
    private static final Logger LOGGER = LogManager.getLogger(CyclicBarrierTest.class);

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(2, () -> LOGGER.info("All sub task is end!"));

        for (int i = 0; i <10; i++) {
            final Integer finalI = i;
            new Thread(() -> run(finalI, barrier)).start();
        }
    }

    public static void run(int index, CyclicBarrier barrier) {
        try {

            LOGGER.info("User index " + index + " is running");
            Thread.sleep(3000L);
            LOGGER.info("User index " + index + " is end");
            barrier.await();

            LOGGER.info("User index " + index + " 后续的工作是...");
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }


}
