package com.wds.java8InAction.ch01;

import org.apache.hadoop.mapreduce.v2.app.webapp.App;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by wangdongsong1229@163.com on 2017/7/30.
 */
public class Test {

    public static void main(String[] args) {
        //Java8
        filterApples(createApple(), Test::isGreenApple);
    }

    //Java8写法
    public static boolean isGreenApple(Apple apple) {
        return "green".equalsIgnoreCase(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        inventory.forEach( apple -> {
           if(p.test(apple)){
               result.add(apple);
           }
        });
        return result;
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
