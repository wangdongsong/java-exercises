package com.wds.concurrency.security.safe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 计数器
 * Created by wds on 2015/10/18.
 */
public class Count {

    private static final Logger LOGGER = LogManager.getLogger(Count.class);
    protected int num = 0;

    public synchronized void add() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }

        num += 1;

        LOGGER.info(Thread.currentThread().getName() + "-" + num);

    }
}
