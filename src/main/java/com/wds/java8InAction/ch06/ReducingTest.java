package com.wds.java8InAction.ch06;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 6.2 归约和汇总
 * Created by wangdongsong1229@163.com on 2017/8/21.
 */
public class ReducingTest {

    public static void main(String[] args) {
        //收集器
        long howManyDishs = Dish.Menu.stream().collect(Collectors.counting());
        //等同于下面
        howManyDishs = Dish.Menu.stream().count();

        //查找流中的最大值和最小值
        Optional<Dish> maxCalorieDish = Dish.Menu.stream().max(Comparator.comparing(Dish::getCalories));
        System.out.println(maxCalorieDish.get().getName());
        maxCalorieDish = Dish.Menu.stream().collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories)));
        System.out.println(maxCalorieDish.get().getName());

        //汇总
        //总热量
        int totalCalories = Dish.Menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(totalCalories);
        totalCalories = Dish.Menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(totalCalories);
        //平均热量
        double avgCalories = Dish.Menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
        System.out.println(avgCalories);
        //一次性计算总和、平均、最大、最小
        IntSummaryStatistics menuStatistics = Dish.Menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics.getAverage());

        //连接字符串
        String joinString = Dish.Menu.stream().map(Dish::getName).collect(Collectors.joining());
        System.out.println(joinString);
        joinString = Dish.Menu.stream().map(Dish::getName).collect(Collectors.joining(","));
        System.out.println(joinString);
    }
}
