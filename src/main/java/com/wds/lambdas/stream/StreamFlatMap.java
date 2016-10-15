package com.wds.lambdas.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wangdongsong1229@163.com on  15-8-15.
 */
public class StreamFlatMap {
    public static void main(String[] args) {
        List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4)).flatMap(numbers -> numbers.stream()).collect(Collectors.toList());

        System.out.println(together);
    }
}
