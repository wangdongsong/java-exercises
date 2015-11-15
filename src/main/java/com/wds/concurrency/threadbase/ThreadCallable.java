package com.wds.concurrency.threadbase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Callable;

/**
 * Created by wds on 2015/10/17.
 */
public class ThreadCallable implements Callable<String> {
    private static final Logger LOGGER = LogManager.getLogger(ThreadCallable.class);

    @Override
    public String call() throws Exception {

        Thread.sleep(500);

        LOGGER.info("This is threadcallabel!");

        return "ThreadCallable";
    }
}
