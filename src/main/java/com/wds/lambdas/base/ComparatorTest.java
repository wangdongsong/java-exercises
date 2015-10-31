package com.wds.lambdas.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

/**
 * Created by wds on 2015/10/28.
 */
public class ComparatorTest {

    private static final Logger LOGGER = LogManager.getLogger(ComparatorTest.class);

    public static void main(String[] args) {
        testSecond();
    }

    public static void testFirst() {
        Comparator<Point> byX = new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Integer.compare(o1.getX(), o1.getY());
            }
        };
    }

    public static void testSecond() {

        List<Point> points = Arrays.asList(new Point(3, 1), new Point(2, 2), new Point(1, 0), new Point(0, 1));

        Comparator<Point> byX = (p1, p2) -> Integer.compare(p1.getX(), p2.getX());

        points.sort(byX);

        LOGGER.info(points);

        Comparator<Point> compareByX = comparing(p -> p.getX());
    }
        

}
