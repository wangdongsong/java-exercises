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

    /**
     * 建立新的结果容器：
     * @return
     */
    @Override
    public Supplier supplier() {
        //return ArrayList::new;
        return () -> new ArrayList<T>();
    }

    /**
     * 将元素添加到结果容器，该方法有两个参数：（1）保存归约结果的累加器（已收集了流中的前n-1项目），（2）第n个元素本身
     * @return
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        //两者等同
        //return List::add;
        return (list, item) -> list.add(item);
    }

    /**
     *
     * 并行操作需要
     *
     * 合并两个结果容器：该方法返回一个供归约操作使用的函数，定义对流的各个子部分进行并行处理时，各个子部分归约所得的累加器
     * 要如何合并
     * @return
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    /**
     * 对结果容器应用最终转换：在遍历完流后，finisher方法必须返回在累积过程的最后要调用的一个函数，以便
     * 将累加器对象转换为整个集合操作的最终结果。
     * @return
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    /**
     * 定义收集器行为
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        //Characteristics.UNORDERED; 归约结果不受流中项目遍历和累积顺序的影响
        //Characteristics.CONCURRENT; accumulator函数可以从多个线程同时调用，且该收集器可以并行归约流
        //Characteristics.IDENTITY_FINISH; 表明完成器方法返回的函数是一个恒等函数。
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }
}
