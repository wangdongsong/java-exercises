package com.wds.lambdas.exercise;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.IntStream;

/**
 * Created by wds on 2015/11/8.
 */
public class FunctionalPerfectNum {
    private static final Logger LOGGER = LogManager.getLogger(FunctionalPerfectNum.class);

    public static void main(String[] args) {
        LOGGER.info(isPerfect(5));
    }

    public static IntStream factorsOf(int num) {
        return IntStream.range(1, num + 1).filter(potential -> num % potential == 0);
    }

    public static int aliquoSum(int num) {
        return factorsOf(num).sum() - num;
    }

    public static boolean isPerfect(int num) {
        return aliquoSum(num) == num;
    }

}
