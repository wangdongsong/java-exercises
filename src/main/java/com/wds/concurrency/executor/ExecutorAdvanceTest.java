package com.wds.concurrency.executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Created by wangdongsong1229@163.com on 2017/2/12.
 */
public class ExecutorAdvanceTest {

    private static final Logger LOGGER = LogManager.getLogger(ExecutorAdvanceTest.class);

    public static void main(String[] args) {

        //baseThreadFactoryTest();

        //baseExceptionTest();

        rejectTaskExceptionTest();
    }

    private static void rejectTaskExceptionTest() {
        //当任务队列为1时，提交任务会失败
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 5, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));
        tpe.setThreadFactory( (thread) -> {
            Thread t = new Thread(thread);
            t.setName("wds" + new Date());
            return t;
        });

        tpe.setRejectedExecutionHandler((thread, executor) -> {
            LOGGER.error(" execute :" + executor.getClass() + " reject task:" + thread.getClass());
        });

        executeTask(tpe);
    }

    private static void executeTask(ThreadPoolExecutor tpe) {
        for (int i = 0; i < 10; i++) {
            tpe.execute(() -> {
                LOGGER.info(Thread.currentThread().getName() + " execute");
            });
        }
    }

    /**
     * 默认情况下抛出JDK的异常，可以实现自定义的异常处理
     */
    private static void baseExceptionTest() {
        //当任务队列为1时，提交任务会失败
        //ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 5, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));

        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 5, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));
        tpe.setThreadFactory( (tt) -> {
            Thread t = new Thread(tt);
            t.setName("wds" + new Date());

            /*
             * 自定义的异常处理机制
             * 只能处理线程中运行的错误，不能处理提交任务给线程池的错误
             */
            t.setUncaughtExceptionHandler((et, e) -> {
                LOGGER.error("自定义异常：" + et.getName() + " error and stack is" + e);
            });

            return t;
        });

        for (int i = 0; i < 10; i++) {
            tpe.execute(() -> {
                String str = null;
                str.indexOf(0);
                LOGGER.info(Thread.currentThread().getName() + " execute");
            });
        }
    }

    private static void baseThreadFactoryTest() {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 5, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        tpe.setThreadFactory( (tt) -> {
            Thread t = new Thread(tt);
            t.setName("wds" + new Date());
            return t;
        });

        executeTask(tpe);

    }

}
