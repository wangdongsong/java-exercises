package com.wds.flume;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by wds on 2015/12/2.
 */
public class LogTest {
    private static final Logger LOGGER = LogManager.getLogger(LogTest.class);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            LOGGER.info("test test王东松");
        }
    }
}
