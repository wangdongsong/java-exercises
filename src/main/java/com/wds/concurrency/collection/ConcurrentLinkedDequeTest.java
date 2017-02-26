package com.wds.concurrency.collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * ConcurrentLinkedQueue仅支持对表头进行操作，而ConcurrentLinkedDeque支持对列头&列尾双向操作
 * Created by wangdongsong1229@163.com on 2017/2/23.
 */
public class ConcurrentLinkedDequeTest {

    private static final Logger LOGGER = LogManager.getLogger(ConcurrentLinkedDequeTest.class);

    public static void main(String[] args) {
        ConcurrentLinkedDeque queue = new ConcurrentLinkedDeque();
        for (int i = 0; i < 10000; i++) {
            queue.add("string " + i);
        }

        new Thread( () -> {
            while (!queue.isEmpty()) {
                queue.pollFirst();
                LOGGER.info(queue.size());
            }
        }).start();

        new Thread( () -> {
            while (!queue.isEmpty()) {
                queue.pollLast();
                LOGGER.info(queue.size());
            }
        }).start();


    }

}
