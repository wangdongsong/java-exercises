package com.wds.concurrency.interrupt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by wds on 2015/10/17.
 */
public class TestInterrupt {
    private static final Logger LOGGER = LogManager.getLogger(TestInterrupt.class);

    public static void main(String[] args) {
        testInterruptThread();
        //testInterruptThreadNotStop();
        LOGGER.info("Stopping application ...");

    }

    /**
     * 对正在运行的线程调用interrupt()，并不会使线程停止运行，只是让线程暂停一会儿。
     * Thread.interupt()对正在运行的线程是不起作用的，只对阻塞的线程起有效
     */
    private static void testInterruptThreadNotStop() {

        Thread t = new Thread(new InterruptThreadNotSop());
        t.start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }

        t.interrupt();


    }

    private static void testInterruptThread() {
        Thread t = new Thread(new InterruptThread());
        t.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            LOGGER.info(e.getMessage(), e);
        }

        t.interrupt();

        LOGGER.info("线程是否中断：" + t.getId() + ":" + t.isInterrupted());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
