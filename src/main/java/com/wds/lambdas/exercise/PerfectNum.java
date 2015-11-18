package com.wds.lambdas.exercise;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 完美数非函数式写法
 * Created by wds on 2015/11/7.
 */
public class PerfectNum {

    private static final Logger LOGGER = LogManager.getLogger(PerfectNum.class);

    private int number;
    private Map<Integer, Integer> cache;

    public static void main(String[] args) {
        PerfectNum num = new PerfectNum(28);
        LOGGER.info(num.isPerfect());
    }

    public PerfectNum(int targetNumber) {
        this.number = targetNumber;
        cache = new HashMap<>();
    }

    public boolean isFactor(int potential) {
        return number % potential == 0;
    }

    public Set<Integer> getFactors() {
        Set<Integer> factors = new HashSet<>();
        factors.add(1);
        factors.add(number);

        for (int i = 2; i < number; i++) {
            if (isFactor(i)) {
                factors.add(i);
            }
        }

        return factors;
    }

    public boolean isPerfect() {
        return aliquotSum() == number;
    }

    public int aliquotSum() {
        if (cache.get(number) == null) {
            int sum = 0;
            for (int i : getFactors()) {
                sum += i;
            }
            cache.put(number, sum - number);
        }

        return cache.get(number);
    }
}
