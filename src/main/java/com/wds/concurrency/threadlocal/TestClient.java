package com.wds.concurrency.threadlocal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by wds on 2015/10/18.
 */
public class TestClient extends Thread{
    private static final Logger LOGGER = LogManager.getLogger(TestClient.class);
    private ThreadMain main;

    public TestClient(ThreadMain main) {
        this.main = main;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            LOGGER.info("thread[" + Thread.currentThread().getName() + "] --> main [" + main.getNextNum() + "]");
            main.getThreadLocal().remove();
        }
    }
}
