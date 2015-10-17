package com.wds.lambdas.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wangdongsong on 15-8-15.
 */
public class StramFilter {

    private static List<String> beginningWithNumbers = Stream.of("a", "1a", "2b", "abc").collect(Collectors.toList());

    private static void forMthod() {
        for (String str : beginningWithNumbers) {
            if (Character.isDigit(str.charAt(0))) {
                System.out.println(str);
            }
        }
    }

    private static void streamMethod() {
        List<String> digitList = Stream.of("a", "1a", "2b", "abc").filter(str -> Character.isDigit(str.charAt(0))).collect(Collectors.toList());
        System.out.println(digitList);
    }

    public static void main(String[] args) {
        forMthod();
        streamMethod();
    }

}
