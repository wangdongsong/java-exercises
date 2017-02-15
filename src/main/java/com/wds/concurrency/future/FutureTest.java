package com.wds.concurrency.future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.util.concurrent.*;

/**
 * submit：可以有返回值，在默认情况下可以catch ExcecutionException捕获异常
 * execute：无返回值，只能通过ThreadFactory的方式捕获异常
 *
 * Future的缺点：
 *
 * Callable和Runnable区别在于Callable可以通过Future取得返回值，但是Future接口通过get()方法获取返回值时是阻塞的。
 * 在任务未完成时，get()方法一直阻塞到此任务完成为止，若任务用时过长，影响性能，这是Futrue的缺点。
 * Created by wangdongsong1229@163.com on 2017/2/14.
 */
public class FutureTest {
    private static final Logger LOGGER = LogManager.getLogger(FutureTest.class);

    public static void main(String[] args) {
        //baseFutureTest();

        //futureCancelTest();

        //futureCancelInterruptedTest();

        //exceptionTest();

        rejectTaskTest();
    }

    private static void rejectTaskTest() {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        tpe.setRejectedExecutionHandler((r, executor) -> {
            LOGGER.info(((FutureTask)r).toString() + " rejected!");
        });

        Future<String> future = tpe.submit( () -> {
            Integer.parseInt("a");
            return "abc";
        });
        tpe.shutdown();

        future = tpe.submit( () -> {
            Integer.parseInt("a");
            return "abc";
        });

    }

    /**
     * 异常可以直接抛出，做异常处理
     */
    private static void exceptionTest() {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        Future<String> future = tpe.submit( () -> {
            Integer.parseInt("a");
            return "abc";
        });
        try {
            //当不调用get()方法时不会抛出异常
            LOGGER.info(future.get());
        } catch (InterruptedException e) {
            LOGGER.info(e.getMessage(), e);
        } catch (ExecutionException e) {
            LOGGER.info(e.getMessage(), e);
        }

        tpe.shutdown();

    }

    /**
     * 中断
     */
    private static void futureCancelInterruptedTest() {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        Future<String> future = tpe.submit( () -> {
            int i = 0;
            while (i == 0) {
                /*
                 * future.cancel(true)，返回为true时，线程不一定能够中断，需要在线程是增加isInterrupted的判断方可中断线程
                 */
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                LOGGER.info("Thread is running！");
            }
            return "abc";
        });
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
        //返回true，
        LOGGER.info(future.cancel(true) + "-" + future.isCancelled());

        tpe.shutdown();

    }

    /**
     * Task 取消
     */
    private static void futureCancelTest() {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        Future<String> future = tpe.submit( () -> {
            Thread.sleep(2000);
            return "abc";
        });
        //返回true，任务还未执行完成，但future.get()会抛出异常
        LOGGER.info(future.cancel(true) + "-" + future.isCancelled());
        try {
            LOGGER.info(future.get());
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (ExecutionException e) {
            //若在Callabled接口中执行异常，则会进入此处做异常处理
            LOGGER.error(e.getMessage(), e);
        }

        //返回false，任务已经执行完成
        LOGGER.info(future.cancel(true) + "-" + future.isCancelled());

        tpe.shutdown();
    }

    private static void baseFutureTest() {

        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        Future<String> future = tpe.submit( () -> {
            return "abc";
        });

        try {
            LOGGER.info(future.get());
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (ExecutionException e) {
            LOGGER.error(e.getMessage(), e);
        }

        tpe.shutdown();
    }

}
