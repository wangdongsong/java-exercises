package com.wds.java8InAction.ch05;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wangdongsong1229@163.com on 2017/8/20.
 */
public class Test {

    public static void main(String[] args) {

        //筛选各异的元素
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4, 6, 8, 10);
        numbers.stream().filter(integer -> integer % 2 == 0).distinct().forEach(System.out::println);

        //截断流
        numbers.stream().filter(i -> i % 2 == 0).limit(3).forEach(System.out::println);

        //跳过
        System.out.println("----------------------");
        numbers.stream().filter(integer -> integer % 2 == 0).skip(2).forEach(System.out::println);
    }


}
