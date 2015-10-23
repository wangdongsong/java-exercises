package com.wds.concurrency.security.safe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by wds on 2015/10/18.
 */
public class SafeTest {
    private static final Logger LOGGER = LogManager.getLogger(SafeTest.class);

    public static void main(String[] args) {
        Count count = new Count();
        for (int i = 0; i < 5; i++) {
            CountThread thread = new CountThread(count);
            thread.start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info("最后的值："  + count.num);

    }

}
