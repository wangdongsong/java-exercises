package com.wds;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by wangdongsong1229@163.com on 2016/9/25.
 */
public class Test {

    private static final Logger LOGGER = LogManager.getLogger(Test.class);

    public static void main(String[] args) {
        LOGGER.info("test message");
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Object job = Stream.of(list).collect(Collectors.toList());
        System.out.println(job.getClass());


        String s1 = new String("abc");
        String s2 = new String("abc");
        System.out.println(s1 == s2);

        String s = new String("1");
        s.intern();
        String s3 = "1";
        System.out.println(s.getClass());
        System.out.println(s3.getClass());
        System.out.println(s == s3);

        String ss = new String("1") + new String("1");
        ss.intern();
        String ss1 = "11";
        System.out.println(ss == ss1);
    }
}
