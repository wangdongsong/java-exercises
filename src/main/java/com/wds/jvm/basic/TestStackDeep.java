package com.wds.jvm.basic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * JVM参数：-Xss128k，栈的大小为128K
 * recursion1递归次数比recursion小，因为recursion1的局部变更多，占用栈空间。
 * Created by wds on 2015/11/11.
 */
public class TestStackDeep {
    private static final Logger LOGGER = LogManager.getLogger(TestStackDeep.class);
    private static int count = 0;

    public static void recursion1(long a, long b, long c) {
        long e = 1, f = 2, g = 3, h = 4, i = 5, k = 6, q = 7, x = 8, y = 9, z = 10;
        count++;
        recursion1(a, b, c);
    }

    public static void recursion2() {
        count++;
        recursion2();
    }

    public static void main(String args[]) {
        try {
            //recursion(1, 2, 3);
            recursion2();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
