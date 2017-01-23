package com.wds.concurrency.juc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Exchanger;

/**
 * Exchange使用
 *
 * 主要用于两个线程之间传递任意数据，多于两个线程时，任意两个线程交换，单数时会阻塞
 * Created by wangdongsong1229@163.com on 2017/1/24.
 */
public class ExchangeTest {

    private static final Logger LOGGER = LogManager.getLogger(ExchangeTest.class);

    public static void main(String[] args) {

        Exchanger<String> exchanger = new Exchanger<>();

        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                try {
                    String name = Thread.currentThread().getName();
                    LOGGER.info(name + " exchange : " + exchanger.exchange(name));
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }).start();
        }



    }


}
