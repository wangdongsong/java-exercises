package com.wds.algorithm.base.hash;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by wangdongsong1229@163.com on  2016/9/25.
 */
public class MyHashMapTest {

    private static final Logger LOGGER = LogManager.getLogger(MyHashMapTest.class);

    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        for (int i = 0; i < 10; i++) {
            map.put(i, i * 10);
        }

        LOGGER.info(map.size());

        LOGGER.info(map.get(9));
    }
}
