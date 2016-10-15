package com.wds.lambdas.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wangdongsong1229@163.com on  15-8-14.
 */
public class StreamMap {

    private static void forToUpper() {
        List<String> list = new ArrayList<>();

        for (String str : Arrays.asList("a", "b", "c")) {
            String upperStr = str.toUpperCase();
            list.add(upperStr);
        }

        System.out.println(list.toString());
    }

    private static void mapToUpper() {
        List<String> collected = Stream.of("a", "b", "c").map(str -> str.toUpperCase()).collect(Collectors.toList());

        System.out.println(collected.toString());
    }

    public static void main(String[] args) {
        forToUpper();
        mapToUpper();
    }
}
