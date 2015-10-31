package com.wds.lambdas.base;

import com.google.common.collect.Collections2;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by wds on 2015/10/30.
 */
public class LambdaConstructorTest {

    private static final Logger LOGGER = LogManager.getLogger(LambdaConstructorTest.class);

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3, 1, 2, 5, 7, 9, 10, 8, 0, 20, 18, 21, 30);

        List<Integer> copyList = new ArrayList<>(list);
        LOGGER.info(copyList);
        Collections.sort(copyList, (x, y) -> Integer.compare(x, y));
        LOGGER.info(copyList);

        //静态方法引用
        copyList = new ArrayList<>(list);
        LOGGER.info(copyList);
        Collections.sort(copyList, Integer::compare);
        LOGGER.info(copyList);

        //实例方法引用
        list.forEach(l -> System.out.print(l));
        list.forEach(System.out::print);

        //构造方法引用
        Stream<String> stringStream = Stream.of("a.txt", "b.txt", "c.txt");
        Stream<File> fileStream = stringStream.map(File :: new);
    }

}
