package com.wds.concurrency.interrupt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 对正在运行的线程调用interrupt()，并不会使线程停止运行，只是让线程暂停一会儿。
 * Thread.interupt()对正在运行的线程是不起作用的，只对阻塞的线程起有效<br>
 * interrupt不会导致线程中断运行
 * Created by wds on 2015/10/18.
 */
public class InterruptThreadNotSop implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(InterruptThreadNotSop.class);

    @Override
    public void run() {

        while (true) {
            LOGGER.info("InterruptThreadNotSop is running");

            for (int i = 0; i < 10001; i++) {
                if (i > 1000) {
                    break;
                }
            }
        }

    }
}
