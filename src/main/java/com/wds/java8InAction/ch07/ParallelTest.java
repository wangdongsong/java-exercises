package com.wds.java8InAction.ch07;

import java.util.stream.LongStream;
import java.util.stream.Stream;

/** 7.1 并行流
 * Created by wangdongsong1229@163.com on 2017/8/24.
 */
public class ParallelTest {

    public static void main(String[] args) {
        System.out.println(seqSum(10));
        System.out.println(parallelSum(10));
    }

    /**
     * 顺序流求和
     * @param n
     * @return
     */
    public static long seqSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).reduce(0L, Long::sum);
    }

    /**
     * 并行流求和
     * @param n
     * @return
     */
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
    }

    /**
     * <code>Stream.iterate</code>性能比较差。但是range方法性能很好
     * @param n
     * @return
     */
    public static long parallelSumOpt(long n) {
        return LongStream.rangeClosed(0L, n).parallel().reduce(0L, Long::sum);
    }

}
