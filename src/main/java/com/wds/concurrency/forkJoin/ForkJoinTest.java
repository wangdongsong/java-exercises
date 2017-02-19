package com.wds.concurrency.forkJoin;

import com.sun.prism.impl.BaseResourceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.*;

/**
 * Created by wangdongsong1229@163.com on 2017/2/18.
 */
public class ForkJoinTest {
    private static final Logger LOGGER = LogManager.getLogger(ForkJoinTest.class);

    public static void main(String[] args) {

        //基本用法
        //baseForJoinActoinTest();

        //baseForJoinTaskTest();

        baseSumTest();

    }

    /**
     * 累计求和
     */
    private static void baseSumTest() {

        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(1, 10);
        pool.submit(task);
        while (true) {
            if (task.isDone()) {
                pool.shutdown();
                LOGGER.info(task.join().intValue());
                break;
            }

        }
    }

    private static class SumTask extends RecursiveTask<Integer> {

        private int beginValue;
        private int endValue;

        public SumTask(int beginValue, int endValue) {
            this.beginValue = beginValue;
            this.endValue = endValue;
        }

        @Override
        protected Integer compute() {
            if ((endValue - beginValue) != 0) {
                int midNum = (endValue + beginValue) / 2;
                SumTask leftTask = new SumTask(beginValue, midNum);
                SumTask rightTask = new SumTask((midNum + 1), endValue);
                this.invokeAll(leftTask, rightTask);
                return leftTask.join() + rightTask.join();
            }
            return endValue;
        }
    }


    private static void baseForJoinTaskTest() {

        ForkJoinPool pool = new ForkJoinPool();
        BaseForkJoinTask t = new BaseForkJoinTask();
        ForkJoinTask t2 = pool.submit(t);

        /*
         * get()和join()都可以获取结果，但get()可以在主线程中做异常处理，而join()不可以，如果有异常，join抛出之后直接程序直接退出
         */
        try {
            LOGGER.info(t2.hashCode() + " ---- " + t2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        LOGGER.info(t2.hashCode() + " ---- " + t2.join());

        while (!t.isDone()) {
            pool.shutdown();
        }
    }

    public static class BaseForkJoinTask extends RecursiveTask<String> {

        @Override
        protected String compute() {
            LOGGER.info("BaseForkJoinTask");
            return "BaseForkJoinTask-ReturnValue";
        }
    }


    private static void baseForJoinActoinTest() {
        ForkJoinPool pool = new ForkJoinPool();
        BaseRecursiveAction task = new BaseRecursiveAction(1, 10);
        pool.submit(task);

        LOGGER.info(task.isDone());


        while (!task.isDone()) {
            pool.shutdown();
        }

        LOGGER.info(task.isCompletedNormally());
    }

    private static class BaseRecursiveAction extends RecursiveAction {

        private int beginValue;
        private int endValue;

        public BaseRecursiveAction(int beginValue, int endValue) {
            this.beginValue = beginValue;
            this.endValue = endValue;
        }

        @Override
        protected void compute() {
            LOGGER.info(Thread.currentThread().getName() + " -------------");
            if (endValue - beginValue > 2) {
                int midNum = (beginValue + endValue) / 2;
                BaseRecursiveAction left = new BaseRecursiveAction(beginValue, midNum);
                BaseRecursiveAction right = new BaseRecursiveAction(midNum, endValue);
                this.invokeAll(left, right);
            } else {
                LOGGER.info("print: " + beginValue + " - " + endValue);
            }
        }
    }

}
