package com.wds.concurrency.juc;

import org.apache.hadoop.hdfs.server.namenode.startupprogress.Phase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Phaser;

/**
 * Created by wangdongsong1229@163.com on 2017/2/9.
 */
public class PhaserTest {
    private static final Logger LOGGER = LogManager.getLogger(PhaserTest.class);

    public static void main(String[] args) {

        //baseTest();

        //arriveAndDeregisterTest
        deregisterTest();

    }

    private static void deregisterTest() {
        Phaser p = new Phaser(3);

        /*
         * 必须为3个线程，若要减少，会阻塞进程。
         */
        new Thread(() -> { methodA(p); }).start();
        new Thread(() -> { methodA(p); }).start();
        new Thread(() -> { methodC(p); }).start();
    }

    private static void methodC(Phaser p) {
        LOGGER.info("-----------before---------" + p.getRegisteredParties());
        LOGGER.info(Thread.currentThread().getName() + " methdC A1 Begin");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info(Thread.currentThread().getName() + " methodC A1 End");

        p.arriveAndDeregister();
        LOGGER.info("-----------" + p.getRegisteredParties());

    }

    private static void baseTest() {

        Phaser p = new Phaser(3);

        /*
         * 必须为3个线程，若要减少，会阻塞进程。
         */
        new Thread(() -> { methodA(p); }).start();
        new Thread(() -> { methodA(p); }).start();
        new Thread(() -> { methodB(p); }).start();


    }

    private static void methodB(Phaser p) {

        try {
            LOGGER.info(Thread.currentThread().getName() + " methdB A1 Begin");
            p.arriveAndAwaitAdvance();
            Thread.sleep(5000);
            LOGGER.info(Thread.currentThread().getName() + " methodB A1 End");

            LOGGER.info(Thread.currentThread().getName() + " methodB A2 Begin");
            p.arriveAndAwaitAdvance();
            Thread.sleep(1000);
            LOGGER.info(Thread.currentThread().getName() + " methodB A2 End");

        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    private static void methodA(Phaser p) {
        LOGGER.info(Thread.currentThread().getName() + " methdA A1 Begin");
        p.arriveAndAwaitAdvance();
        LOGGER.info(Thread.currentThread().getName() + " methodA A1 End");

        LOGGER.info(Thread.currentThread().getName() + " methodA A2 Begin");
        p.arriveAndAwaitAdvance();
        LOGGER.info(Thread.currentThread().getName() + " methodA A2 End");
    }


}
