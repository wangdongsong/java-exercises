package com.wds.concurrency.interrupt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * interrupt方法实际上只是设置了一个中断状态，当该线程由于下列原因而受阻时，中断状态就会起作用。<br>
 * <pre>
 *     (1)线程在调用Object类的wait、wait(long)、wait(long, int)方法或join()、join(long)、join(long,int)、sleep(long)、sleep(loing, int)方法过程
 *     受阴，则中断状态将被清除，它还将收到一个InterruptedException异常，可以通过捕获异常终止线程执行。
 *     (2)线程在可中断的通道上的IO操作受阴时，则该通道关闭，并收到ClosedByInterruptException的异常
 *     (3)SocketException
 *
 * </pre>
 * <p>
 * Created by wds on 2015/10/17.
 */
public class InterruptThread implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(InterruptThread.class);

    /**
     * 可以中断
     */
    public void run2() {
        boolean stop = false;

        while (!stop) {
            LOGGER.info(Thread.currentThread().getId() + " = Interrupt Thread is running...");
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
        }

        LOGGER.info("Interrupt Thread exiting under request...");
    }

    /**
     * 可以中断
     */
    public void run() {
        boolean stop = false;
        try {
            while (!stop) {
                LOGGER.info(Thread.currentThread().getId() + " = Interrupt Thread is running...");
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
