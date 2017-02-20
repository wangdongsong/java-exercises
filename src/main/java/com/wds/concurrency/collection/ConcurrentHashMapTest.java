package com.wds.concurrency.collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 非阻塞队列的特别就是队列中无数据时，操作队列出现异常或返回null，不具有等待/阻塞的特点，JDK并发包中，常见的非阻塞队列有
 * <code>ConcurrentHashMap</code>
 * ConcurrentSkipListMap
 * ConcurrentSkipListSet
 * ConcurrentLinkedQueue
 * ConcurrentLinkedDeque
 * CopyOnWriteArrayList
 * ConpyOnWriteArraySet
 * <br>
 *
 * ConcurrentHashMap线程安全，支持并发，但不支持排序，若要线程安全&排序，使用<code>ConcurrentSkipListMap</code>
 *
 * <br>
 * Created by wangdongsong1229@163.com on 2017/2/20.
 */
public class ConcurrentHashMapTest {

    private static final Logger LOGGER = LogManager.getLogger(ConcurrentHashMapTest.class);

    public static void main(String[] args) {

        //baseHashMapTest();

        baseConcurrentHashMapTest();
    }

    /**
     * HashpMap多线程下的问题，线程非安全的
     */
    private static void baseHashMapTest() {
        Map hashMap = new HashMap<String, String>();

        for (int k = 0; k < 100; k++) {
            new Thread(() -> {
                for (int i = 0; i < 5000; i++) {
                    //LOGGER.info(Thread.currentThread().getName() + " -"  + i);
                    hashMap.put("wds" + i, "wds" + i);
                }
            }).start();
        }

        /*
         * HashMap在边读边remove会有异常
         */
        new Thread(() -> {
            Iterator iter = hashMap.keySet().iterator();
            while (iter.hasNext()){
                LOGGER.info(hashMap.get(iter.next()));
                hashMap.remove(iter.next());
            }
        }).start();


        LOGGER.info(hashMap.size());
    }

    private static void baseConcurrentHashMapTest() {
        ConcurrentHashMap hashMap = new ConcurrentHashMap<String, String>();

        for (int k = 0; k < 100; k++) {
            new Thread(() -> {
                for (int i = 0; i < 5000; i++) {
                    //LOGGER.info(Thread.currentThread().getName() + " -"  + i);
                    hashMap.put("wds" + i, "wds" + i);
                }
            }).start();
        }
        /*
         * ConcurrentHashMap在边读边remove不会有异常
         */
        new Thread(() -> {
            Iterator iter = hashMap.keySet().iterator();
            while (iter.hasNext()){
                hashMap.remove(iter.next());
                LOGGER.info(hashMap.get(iter.next()));
            }
        }).start();


        LOGGER.info(hashMap.size());
    }
}
