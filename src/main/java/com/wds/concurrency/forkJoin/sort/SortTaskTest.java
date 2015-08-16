package com.wds.concurrency.forkJoin.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;


/**
 * Created by wangdongsong on 15-8-9.
 */
public class SortTaskTest {
    private static final int NARRAY = 16;

    public static void main(String[] args) {
        long[] array = new long[NARRAY];

        //准备数据
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextLong() % 100;
            System.out.println("Initial Array:" + Arrays.toString(array));
        }

        System.out.println("----------------------------------------");

        ForkJoinTask sort = new SortTask(array);
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(sort);
        pool.shutdown();
        try {
            pool.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(checkSorted(array));

    }

    private static boolean checkSorted(long[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > (array[i + 1])) {
                return false;
            }
        }
        return true;
    }


}
