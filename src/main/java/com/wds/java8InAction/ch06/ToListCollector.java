package com.wds.java8InAction.ch06;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;


/**
 * 6.5 自定义收集器
 * Created by wangdongsong1229@163.com on 2017/8/23.
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    @Override
    public Supplier supplier() {
        //return ArrayList::new;
        return () -> new ArrayList<T>();
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        //两者等同
        //return List::add;
        return (list, item) -> list.add(item);
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }
}
