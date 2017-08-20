package com.wds.java8InAction.ch04;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 第4章 引入流
 * Created by wangdongsong1229@163.com on 2017/8/19.
 */
public class Test {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, "meta"),
                new Dish("beef", false, 700, "meta"),
                new Dish("chicken", false, 400, "meta"),
                new Dish("french fries", true, 530, "other"),
                new Dish("rice", true, 350, "other"),
                new Dish("season fruit", true, 120, "other"),
                new Dish("pizza", true, 550, "other"),
                new Dish("prawns", false, 300, "fish"),
                new Dish("salmon", false, 450, "fish")
        );

        //Lambda写法
        //查找到低于400卡路里，并按卡路里排序，返回这些食物的名称
        List<String> lowCaloricDishesName = menu.stream().filter(dish -> dish.getCalories() < 400).sorted(Comparator.comparing(Dish::getCalories)).map(Dish::getName).collect(Collectors.toList());
        lowCaloricDishesName.forEach(System.out::println);
    }

}
