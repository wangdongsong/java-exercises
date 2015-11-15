package com.wds.lambdas.stream.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by wds on 2015/10/31.
 */
public class StreamBase {
    private static final Logger LOGGER = LogManager.getLogger(StreamBase.class);

    public static void main(String[] args) {
        //IntStream.iterate(1, i -> i * 2).limit(10).forEachOrdered(System.out::println);

        //会发生自动的装箱、拆箱，数据大时会影响性能
        Optional<Integer> max = Arrays.asList(3, 2, 1, 0, 9, 10).stream().map(i -> i + 1).max(Integer::compareTo);
        LOGGER.info(max.get());

        //使用原生值流
        OptionalInt optionalIntMax = IntStream.rangeClosed(1, 5).map(i -> i+1).max();
        LOGGER.info(optionalIntMax.getAsInt());

        //流的装箱
        Stream<Integer> is = IntStream.range(1, 5).map(i -> i*2).boxed();
        //拆箱
        Stream<Integer> integerStream = Stream.of(1, 2);
        IntStream intStream = integerStream.mapToInt(Integer::intValue);
    }
}
