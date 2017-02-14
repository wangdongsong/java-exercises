package com.wds.concurrency.executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.concurrent.*;
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

        //rejectTaskExceptionTest();

        abortTaskPolicyTest();
    }

    /**
     * AbortPolicy：抛出RejectedExecutionException异常
     * CallerRunsPolicy：任务拒绝时，会使用调用线程池的Thread线程对象处理被拒绝的任务
     * DiscardOldestPolicy：线程池放弃等待队列中最旧的未处理任务，然后将拒绝的任务添加到等待队列中
     * DiscardPolicy：线程池丢弃被拒绝的任务
     */
    private static void abortTaskPolicyTest() {

        //AbortPolicy
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2), new ThreadPoolExecutor.AbortPolicy());

        //CallerRunsPolicy
        tpe = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2), new ThreadPoolExecutor.CallerRunsPolicy());
        //executeTask(tpe);

        tpe = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1), new ThreadPoolExecutor.DiscardOldestPolicy());
        //executeTask(tpe);

        tpe = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1), new ThreadPoolExecutor.DiscardPolicy());
        executeTask(tpe);

    }

    private static void rejectTaskExceptionTest() {
        //当任务队列为1时，提交任务会失败
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2));
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
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage(), e);
                }
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
