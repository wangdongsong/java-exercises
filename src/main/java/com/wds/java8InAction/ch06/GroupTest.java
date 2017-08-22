package com.wds.java8InAction.ch06;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 6.3 分组
 * Created by wangdongsong1229@163.com on 2017/8/22.
 */
public class GroupTest {
    private enum CaloricLevel {DIET, NORMAL, FAT}

    public static void main(String[] args) {
        //分组
        //简单分组
        Map<Dish.Type, List<Dish>> dishesByType = Dish.Menu.stream().collect(Collectors.groupingBy(Dish::getType));

        //复杂分组
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = Dish.Menu.stream().collect(Collectors.groupingBy(dish ->{
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        }));

        //多级分组
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = Dish.Menu.stream().collect(Collectors.groupingBy(Dish::getType,
                Collectors.groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                })
        ));

        //按子组收集数据
        Map<Dish.Type, Long> typesCount = Dish.Menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        //每个子类别中热量高的
        Map<Dish.Type, Optional<Dish>> mostCaloricByType = Dish.Menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
        Map<Dish.Type, Dish> mostCaloricByType2 = Dish.Menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));

        //按类别计算总热量
        //groupingBy联合使用的例子
        Map<Dish.Type, Integer> totoalCaloriesByType = Dish.Menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.summingInt(Dish::getCalories)));

        //groupingBy与mapping结合的例子
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = Dish.Menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(
                dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }, Collectors.toSet()
        )));

        caloricLevelsByType = Dish.Menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(
                dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }, Collectors.toCollection(HashSet::new)
        )));
    }
}
