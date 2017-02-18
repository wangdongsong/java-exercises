package com.wds.concurrency.forkJoin;

import com.sun.prism.impl.BaseResourceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by wangdongsong1229@163.com on 2017/2/18.
 */
public class ForkJoinTest {
    private static final Logger LOGGER = LogManager.getLogger(ForkJoinTest.class);

    public static void main(String[] args) {

        //基本用法
        baseForJoinTest();

    }

    private static void baseForJoinTest() {
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
