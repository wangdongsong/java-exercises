package com.wds.concurrency.threadbase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by wds on 2015/10/17.
 */
public class ThreadBase extends Thread {

    private static final Logger LOGGER = LogManager.getLogger(ThreadBase.class);

    @Override
    public void run() {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }

        LOGGER.info("This is ThreadBase");

    }
}
