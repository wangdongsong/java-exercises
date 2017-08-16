package com.wds.java8InAction.ch02;

import com.wds.java8InAction.ch01.Apple;
import org.apache.hadoop.mapreduce.v2.app.webapp.App;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by wangdongsong1229@163.com on 2017/8/15.
 */
public class Test {

    public static void main(String[] args) {
        List<Apple> greenApple = filterApples(createApple(), new AppleColorPredicate());
        greenApple.forEach((apple) -> System.out.println(apple));

        //java8
        filter(createApple(), (Apple apple) -> "red".equalsIgnoreCase(apple.getColor()));

        //排序
        createApple().sort((Apple a1, Apple a2) -> a1.getColor().compareTo(a2.getColor()));
    }

    public static <T> List<T> filter(List<T> inventory, Predicate<T> p) {
        List<T> result = new ArrayList<T>();
        for (T e : inventory) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
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
