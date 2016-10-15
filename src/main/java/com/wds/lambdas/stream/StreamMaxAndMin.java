package com.wds.lambdas.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by wangdongsong1229@163.com on  15-8-15.
 */
public class StreamMaxAndMin {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("abc", "def", "hij", "4523", "123456", "a");

        //min
        String minStr = list.stream().min(Comparator.comparing(str -> str.length())).get();
        System.out.println(minStr);

        //max
        String maxStr = list.stream().max(Comparator.comparing(str -> str.length())).get();
        System.out.println(maxStr);
    }

}
