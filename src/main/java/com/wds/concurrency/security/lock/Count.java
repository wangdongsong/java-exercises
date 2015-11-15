package com.wds.concurrency.security.lock;

import com.wds.concurrency.threadbase.ThreadBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wds on 2015/10/18.
 */
public class Count {
    private static final Logger LOGGER = LogManager.getLogger(Count.class);

    private final Lock lock = new ReentrantLock();

    public void get() {
        lock.lock();

        LOGGER.info(Thread.currentThread().getName() + " get begin");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }

        LOGGER.info(Thread.currentThread().getName() + " get end");

        lock.unlock();
    }

    public void put() {

        lock.lock();

        LOGGER.info(Thread.currentThread().getName() + " put begin");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }

        LOGGER.info(Thread.currentThread().getName() + " put end");

        lock.unlock();
    }
}
