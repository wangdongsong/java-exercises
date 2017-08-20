package com.wds.java8InAction.ch05;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

        //-----------------Mapping---------------------
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream().map(String::length).collect(Collectors.toList());

        //流扁平化
        //将每个单词折分成字符数组
        List<String[]> result = words.stream().map(word -> word.split("")).distinct().collect(Collectors.toList());
        for (String[] strings : result) {
            System.out.println(strings[0]);
        }
        //flatMap扁平化
        List<String> r = words.stream().map(word -> word.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        r.forEach(System.out::print);
    }


}
