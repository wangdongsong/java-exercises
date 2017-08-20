package com.wds.java8InAction.ch05;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by wangdongsong1229@163.com on 2017/8/20.
 */
public class Test {

    public static void main(String[] args) {

        //筛选各异的元素
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4, 6, 8, 10);
        numbers.stream().filter(integer -> integer % 2 == 0).distinct().forEach(System.out::println);

        //截断流
        numbers.stream().filter(i -> i % 2 == 0).limit(3).forEach(System.out::println);

        //跳过
        System.out.println("----------------------");
        numbers.stream().filter(integer -> integer % 2 == 0).skip(2).forEach(System.out::println);

        //-----------------Mapping---------------------
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream().map(String::length).collect(Collectors.toList());

        //流扁平化
        //将每个单词折分成字符数组
        List<String[]> result = words.stream().map(word -> word.split("")).distinct().collect(Collectors.toList());
        for (String[] strings : result) {
            System.out.println(strings[0]);
        }
        //flatMap扁平化
        List<String> r = words.stream().map(word -> word.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        r.forEach(System.out::print);

        //映射练习
        //(1)给定个数字列表，返回一个由每个数的平方构成的列表
        List<Integer> number = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = number.stream().map(n -> n * n).collect(Collectors.toList());
        //(2)给定两个数字列表，返回所有的数对
        List<Integer> number2 = Arrays.asList(3, 4);
        List<int[]> pairs = number.stream().flatMap(i -> number2.stream().map(j -> new int[]{i, j })).collect(Collectors.toList());

        //查找和匹配
        //allMatch、anyMatch、noneMatch、findFirst、findAny

        System.out.println("--------------归约------------");
        //归约
        //元素求和
        //reduce接受两个参数：1是初始值为0，一个是BinaryOperator<T>来将两个元素结合起来产生一个新值
        int sum = number.stream().reduce(0, (a, b) -> a + b).intValue();
        System.out.println(sum);
        int sum2 = number.stream().reduce(0, Integer::sum);
        //无初始值的reduce
        Optional<Integer> sum3 = number.stream().reduce((a, b) -> a + b);
        System.out.println(sum3.orElse(0));
        //最大值、最小值
        Optional<Integer> max = number.stream().reduce(Integer::max);
        Optional<Integer> min = number.stream().reduce(Integer::min);
        System.out.println("max=" + max.orElse(0) + " min=" + min.orElse(0));

        //数值流
        //以下两个求和相等
        //原始方式
        words.stream().map(String::length).reduce(0, Integer::sum);
        //映射到数值流
        words.stream().mapToLong(String::length).sum();

        //数值流，转换回对象流
        IntStream intStream = words.stream().mapToInt(String::length);
        Stream<Integer> stream = intStream.boxed();

        //数值流 默认值
        OptionalInt maxOptional = words.stream().mapToInt(String::length).max();
        int maxValue = maxOptional.orElse(1);

        //数值流，数值范围
        IntStream evenNumbers = IntStream.range(1, 100).filter(n -> n % 2 == 0);

        //生成勾股数值流
        Stream<int[]> pythagoreanTriples = IntStream.range(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).mapToObj(b -> new int[]{a, b, (int)Math.sqrt(a * a + b * b)}));

        //构建流
        Stream<String> streamStr = Stream.of("java8", "lambda", "in", "action");
        Stream<String> emptyStream = Stream.empty();
        //创建无限流
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);
        //创建斐波纳契
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(20);
        //生成流
        Stream.generate(Math::random).forEach(System.out::println);
    }


}
