package com.wds.lambdas.base;

import org.apache.avro.generic.GenericData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * Created by wangdongsong1229@163.com on 2017/3/2.
 */
public class BaseLambdaTest {
    private final static Logger LOGGER = LogManager.getLogger(BaseLambdaTest.class);

    public static void main(String[] args) {

        /*
         * 生产Integer实例的集合，接下来通过转换生成一个Point实例集合，最后寻找到距离原点最远的点到原点的距离
         */
        unLambdaMethod();

        lambdaMethod();

    }

    private static void lambdaMethod() {
        Stream<Integer> intStream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        OptionalDouble maxDistance = intStream.map(i -> new Point(i % 3, i / 1)).mapToDouble(p -> p.distance(0, 0)).max();
        LOGGER.info("unLambdaMethod maxDistance= " + maxDistance.getAsDouble());
    }

    /**
     * 非Lambda的方式
     */
    private static void unLambdaMethod() {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Point> pointList = new ArrayList<>();
        for (Integer i : intList) {
            pointList.add(new Point(i % 3, i / 1));
        }

        double maxDistance = Double.MIN_VALUE;
        for (Point p : pointList) {
            maxDistance = Math.max(p.distance(0, 0) , maxDistance);
        }

        LOGGER.info("unLambdaMethod maxDistance= " + maxDistance);
    }
}
