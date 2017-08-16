package com.wds.java8InAction.ch02;

import com.wds.java8InAction.ch01.Apple;
import org.apache.hadoop.mapreduce.v2.app.webapp.App;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangdongsong1229@163.com on 2017/8/15.
 */
public class Test {

    public static void main(String[] args) {
        List<Apple> greenApple = filterApples(createApple(), new AppleColorPredicate());
        greenApple.forEach((apple) -> System.out.println(apple));
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePrediccate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }


    public static List<Apple> createApple(){
        List<Apple> list = new ArrayList<>();
        list.add(new Apple("green", 155));
        list.add(new Apple("red", 145));
        list.add(new Apple("green", 175));
        list.add(new Apple("red", 155));
        list.add(new Apple("green", 135));
        list.add(new Apple("read", 153));
        list.add(new Apple("green", 195));
        return list;
    }
}
