package com.wds.java8InAction.ch06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

/**
 * 6.6 开发自己的收集器
 * Created by wangdongsong1229@163.com on 2017/8/23.
 */
public class MainTest {

    public static void main(String[] args) {
        partitionPrimes(20);

        //优化方法
        /*
         * 可能的优化是看被测试数是不是能够被质数整除。如果除数本身不是质数则无须测试。
         * 可以用被测试数之前的质数来测试。但需要开发一个收集器，在收集过程中没有办法访问部分结果，意味着在测试
         * 某个数字是否为质数时，没有办法访问已经找到其它质数
         */
        partitionPrimesOpt(20);
    }

    private static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        //将前n个自然数按质数和非质数分区
        return IntStream.rangeClosed(2, n).boxed().collect(partitioningBy(candidate -> isPrime(candidate)));
    }

    private static Map<Boolean, List<Integer>> partitionPrimesOpt(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(new PrimeNumbersCollector());
    }

    private static Map<Boolean, List<Integer>> partitionPrimesWithCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(
                () -> new HashMap<Boolean, List<Integer>>() {{
                    put(true, new ArrayList<Integer>());
                    put(false, new ArrayList<Integer>());
                }},
                (acc, candidate) -> {
                    acc.get(isPrime(acc.get(true), candidate)).add(candidate);
                }, (map1, map2) -> {
                    map1.get(true).addAll(map2.get(true));
                    map1.get(false).addAll(map2.get(false));
                });
    }

    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidate).noneMatch(i -> candidate % i == 0);
    }

    public static boolean isPrime(List<Integer> primes, int candidate) {
        return primes.stream().noneMatch(i -> candidate % i == 0);
    }

}
