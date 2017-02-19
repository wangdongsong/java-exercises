package com.wds.concurrency.forkJoin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * 核心方法练习
 * Created by wangdongsong1229@163.com on 2017/2/19.
 */
public class ForkJoinCoreMethodTest {
    private static final Logger LOGGER = LogManager.getLogger(ForkJoinCoreMethodTest.class);

    public static void main(String[] args) {
        baseExecuteTest();
    }

    private static void baseExecuteTest() {
        ForkJoinPool pool = new ForkJoinPool();

        pool.execute( () -> {
            LOGGER.info(Thread.currentThread().getName() + Thread.currentThread().getClass());
        });

        LOGGER.info(pool.getParallelism());
        LOGGER.info(pool.getPoolSize());
        LOGGER.info(pool.getQueuedSubmissionCount());


        //调用shutdown之后，任务不能再提交
        //调用此方法后，get()不会出异常，调用shuwdownNow之后再调用 get方法会出现CancellationException异常
        //pool.shutdown();

        //execute无返回值 ，但是可以通过task.get()获取
        ForkJoinTest.BaseForkJoinTask task = new ForkJoinTest.BaseForkJoinTask();
        pool.execute(task);

        //submit有返回值，可以通过ForkJoinTask获得返回值
        ForkJoinTask futureTask = pool.submit(task);
        try {
            LOGGER.info(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //判断任务是否出现异常
        LOGGER.info(futureTask.isCompletedAbnormally());
        //判断任务是否正常执行完成
        LOGGER.info(futureTask.isCompletedNormally());
        //获取返回的异常
        LOGGER.info(futureTask.getException());

        //ForkJoin也可以处理Callable接口
        futureTask = pool.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                return Thread.currentThread().getName() + " my is return value --Callable";
            }
        });

        //获取并行的数量，与CPU的内核数量有关
        LOGGER.info(pool.getParallelism());
        //获取任务池的大小
        LOGGER.info(pool.getPoolSize());
        //获取已经提交但尚未被执行的任务数量
        LOGGER.info(pool.getQueuedSubmissionCount());
        //判断队列中是否有未执行的任务
        LOGGER.info(pool.hasQueuedSubmissions());
        //获得任务的总数
        LOGGER.info(pool.getQueuedTaskCount());
        //获得活动的线程个数
        LOGGER.info(pool.getActiveThreadCount());
        //获得偷窃的任务个数
        LOGGER.info(pool.getStealCount());
        //获得正在运行并且不在阻塞状态下的线程个数
        LOGGER.info(pool.getRunningThreadCount());
        //判断任务池是否是静止未执行任务的状态
        LOGGER.info(pool.isQuiescent());

        try {
            LOGGER.info(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000);
            LOGGER.info(task.get());
            LOGGER.info(futureTask.get());
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (ExecutionException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }
}
