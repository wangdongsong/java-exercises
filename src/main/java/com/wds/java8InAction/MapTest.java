package com.wds.java8InAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

/**
 * Created by wangdongsong1229@163.com on 2017/9/1.
 */
public class MapTest {


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("2");
        Map<String, List<String>> maps = new HashMap<>();
        maps.put("1", null);
        maps.put("2", list);

        maps.entrySet().stream().filter((entry) -> entry.getValue() != null).forEach(stringListEntry -> {
            System.out.println(stringListEntry.getKey());
        });

        BiPredicate<Integer, String> condition = (i, s)-> i>20 && s.startsWith("R");
        System.out.println(condition.test(10,"Ram"));
        System.out.println(condition.test(30,"Shyam"));
        System.out.println(condition.test(30,"Ram"));
    }

}
