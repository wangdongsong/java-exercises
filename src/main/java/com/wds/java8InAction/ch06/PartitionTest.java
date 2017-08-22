package com.wds.java8InAction.ch06;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

/**
 * 6.4 分区
 *
 * 分区是分组的特殊情况：由一个谓词（返回一个布尔的函数）作为分类函数，它称分区函数。
 * Created by wangdongsong1229@163.com on 2017/8/22.
 */
public class PartitionTest {

    public static void main(String[] args) {

        //区分素食和非素食
        Map<Boolean, List<Dish>> partitionedMenu = Dish.Menu.stream().collect(partitioningBy(Dish::isVegetarian));
        List<Dish> vegetarianDishes = partitionedMenu.get(true);
        //过滤器，等同上面的效果
        vegetarianDishes = Dish.Menu.stream().filter(Dish::isVegetarian).collect(toList());

        //分区的优势
        /*
         * 分区的好处在于保留了分区函数返回true或false的两套流元素列表。可以做二级收集器
         */
        //对素食和非素食子流按类型分组，得到二级Map
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = Dish.Menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));

        //找到素食和非素食中热量最高的菜
        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = Dish.Menu.stream().collect(partitioningBy(Dish::isVegetarian, collectingAndThen(maxBy(Comparator.comparing(Dish::getCalories)), Optional::get)));

        //将数字按质数和非质数分区
        IntStream.rangeClosed(2, 100).noneMatch(i -> 100 % i == 0);
    }

}
