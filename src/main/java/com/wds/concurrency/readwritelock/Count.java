package com.wds.concurrency.readwritelock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 单独使用ReadLock和WriteLock
 * Created by wds on 2015/10/18.
 */
public class Count {
    private static final Logger LOGGER = LogManager.getLogger(Count.class);
    private static final ReadWriteLock rrwl = new ReentrantReadWriteLock();

    public void get() {
        rrwl.readLock().lock();

        try {
            LOGGER.info(Thread.currentThread().getName() + "read start");
            Thread.sleep(1000L);
            LOGGER.info(Thread.currentThread().getName() + " read end");
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            rrwl.readLock().unlock();
        }

    }

    public void put() {
        //写锁，具有阻塞性
        rrwl.writeLock().lock();

        try {
            LOGGER.info(Thread.currentThread().getName() + " write start");
            Thread.sleep(2000L);
            LOGGER.info(Thread.currentThread().getName() + " write end");

        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            rrwl.writeLock().unlock();
        }
    }
}
