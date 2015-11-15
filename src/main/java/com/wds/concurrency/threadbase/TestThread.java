package com.wds.concurrency.threadbase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by wds on 2015/10/17.
 */
public class TestThread {
    private static final Logger LOGGER = LogManager.getLogger(TestThread.class);

    public static void main(String[] args) {
        testThreadBase();
        testThreadRunnalbe();
        testThreadCallable();
    }

    private static void testThreadRunnalbe() {
        ThreadRunnable threadRunnable = new ThreadRunnable();
        new Thread(threadRunnable).start();
    }

    private static void testThreadCallable() {

        ThreadCallable thread = new ThreadCallable();
        FutureTask futureTask = new FutureTask(thread);
        new Thread(futureTask).start();

        try {
            LOGGER.info("TestThread output: " + futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error(e.getMessage(), e);
        }


    }

    private static void testThreadBase() {
        ThreadBase threadBase = new ThreadBase();
        threadBase.start();
    }
}
