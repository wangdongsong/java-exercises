package com.wds.lambdas.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wangdongsong1229@163.com on  15-8-14.
 */
public class StreamCollect {

    public static void main(String[] args) {
        List<String> collected = Stream.of("a", "b", "c", "d", "e").collect(Collectors.toList());

        System.out.println(collected.toString());

    }
}
