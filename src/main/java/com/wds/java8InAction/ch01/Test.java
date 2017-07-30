package com.wds.java8InAction.ch01;

import org.apache.hadoop.mapreduce.v2.app.webapp.App;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangdongsong1229@163.com on 2017/7/30.
 */
public class Test {

    public static void main(String[] args) {

    }


    /**
     * 原始写法
     * @param inventory
     * @return
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equalsIgnoreCase(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 原始写法
     * @param inventory
     * @return
     */
    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> createAppl(){
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
