package com.wds.java8InAction.ch07;

import java.util.concurrent.RecursiveTask;

/**
 * 7.2 分支/合并框架
 * Created by wangdongsong1229@163.com on 2017/8/24.
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long>{

    private final long[] numbers;
    private final int start;
    private final int end;

    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSeq();
        }
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();

        ForkJoinSumCalculator rigthTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        //rigthTask.fork();

        Long rightResult = rigthTask.compute();
        Long leftResult = leftTask.join();

        return rightResult + leftResult;
    }

    private Long computeSeq() {
        long sum = 0;
        for(int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
