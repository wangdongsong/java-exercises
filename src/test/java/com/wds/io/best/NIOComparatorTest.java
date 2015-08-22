package com.wds.io.best;

import com.wds.io.nio.NIOComparator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * NIOComparator的测试类
 * Created by WANGDONGSONG846 on 2015-08-22.
 */

public class NIOComparatorTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(NIOComparatorTest.class);

    private NIOComparator comparator;

    @Before
    public void setUp() {
       comparator = new NIOComparator();
    }

    @Test
    public void ioMethodTest() {
        long start = System.currentTimeMillis();
        comparator.ioMethod("D:/Dev/io.txt");
        long end = System.currentTimeMillis();
        System.out.println("io=" + (end - start));
    }

    @Test
    public void byteMethodTest() {
        long start = System.currentTimeMillis();
        comparator.byteMethod("D:/Dev/byte.txt");
        long end = System.currentTimeMillis();
        System.out.println("byte=" + (end - start));
    }

    @Test
    public void mapMethodTest() {
        long start = System.currentTimeMillis();
        comparator.mapMethod("D:/Dev/map.txt");
        long end = System.currentTimeMillis();
        System.out.println("map=" + (end - start));
    }
}
