package com.wds.concurrency.collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * poll()当没有获得数据时返回null，如果有数据时则移除表头，将表头进行返回
 * element()当没有数据时出现NoSuchElementException，如果有数据时则返回表头项
 * peek()当没有数据时返回null，如果有数据时则不移除表头，并将表头进行返回
 * Created by wangdongsong1229@163.com on 2017/2/23.
 */
public class ConcurrentLinkedQueueTest {

    private static final Logger LOGGER = LogManager.getLogger(ConcurrentLinkedQueueTest.class);

    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();

        new Thread( () ->{
            for (int i = 0; i < 50; i++) {
                queue.add("threadA" + (i + 1));
            }
        }).start();

        new Thread( () ->{
            for (int i = 0; i < 50; i++) {
                queue.add("threadB" + (i + 1));
            }
        }).start();

        Thread.sleep(500);

        LOGGER.info(queue.size());
    }

}
