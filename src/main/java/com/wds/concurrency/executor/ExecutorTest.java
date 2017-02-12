package com.wds.concurrency.executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.*;

/**
 * A：被执行的Runnable的数量
 * B：corePoolSize
 * C：maxinumPoolSize
 * D：代表A-B（A>B）
 * E：代表new LinkedBlockingQueue<Runnable>，无参构造
 * F：SynchronousQueue队列
 * G：代表KeepAliveTime
 *
 * 当使用E条件时，C和G参数无效
 *
 * A>B&A<=C&&F：C和G有效，并且马上创建线程运行任务，而不把D放到F，D执行完任务后在指定时间后发生超时时将D清除
 *
 * A>B&A>C&&F：则处理C任务，其它任务则不处理抛出异常
 *
 * Created by wangdongsong1229@163.com on 2017/2/11.
 */
public class ExecutorTest {
    private final static Logger LOGGER = LogManager.getLogger(ExecutorTest.class);

    public static void main(String[] args) {

        //cacheThreadPoolTest();

        //fixedThreadPoolTest();

        //threadPoolExecutorTest();

        //threadPoolExecutorTest2();

        shutDownTest();
    }

    /**
     * shutDown使用当前未执行完的线程继续执行，而不再添加新的task，该方法不会阻塞，执行结束之后main线程结束，
     * 而线程池继续运行直到所有任务执行完才停止，如果不调用此方法，线程池会一直保持下去
     *
     * shutDownNow中断所有正在执行的任务，并抛出InterruptedExecution异常，清除未执行的队列
     */
    private static void shutDownTest() {

        ThreadPoolExecutor es = new ThreadPoolExecutor(7, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        executeTest(es);
        es.shutdown();
        //再次执行任务时异常
        //executeTest(es);

        LOGGER.info(es.isShutdown());
        LOGGER.info(es.isTerminating());
        LOGGER.info(es.isTerminated());

        while (true) {
            if (es.isTerminated()) {
                LOGGER.info("pool is closed");
                break;
            }
        }
    }

    private static void threadPoolExecutorTest2() {

        /*
         * 如果使用SynchronousQueue时，maxinumPoolSize参数起起作用
         */
        ThreadPoolExecutor es = new ThreadPoolExecutor(7, 20, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(true));

        executeTest(es);
    }

    /**
     * 自定义线程池1
     *
     * BlockingQueue是接口，常用实现类有LinkedBlockingQueue和ArrayBlockingQueue。使用LinkedBlockingQueue的好处在于没有大小限制
     *
     * 优点是容量非常大，所以执行execute不会抛出异常，而线程池运行的线程数不会超地corePoolSize值
     */
    private static void threadPoolExecutorTest() {

        /*
         * 特别说明：
         *
         * 队列使用LinkedBlockingQueue类，也就是如果线程数量>corePoolSize时将多余的任务放入队列中，同一时间最多只能有corePoolSize线程在运行
         *
         * 如果使用LinkedBlockingQueue，则maxinumPoolSize的参数不起作用
         */
        ThreadPoolExecutor es = new ThreadPoolExecutor(7, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        executeTest(es);
    }

    private static void executeTest(ThreadPoolExecutor es) {
        for (int i = 0; i < 10; i++) {
            es.submit(() -> {
                LOGGER.info(Thread.currentThread().getName() + " threadPoolExecutor submit running");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            });

            es.execute(() -> {
                LOGGER.info(Thread.currentThread().getName() + " threadPoolExecutor execute running");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            });
        }

        LOGGER.info(es.getCorePoolSize());
        LOGGER.info(es.getPoolSize());
        LOGGER.info(es.getActiveCount());
        LOGGER.info(es.getQueue().size());
    }

    private static void fixedThreadPoolTest() {
        ExecutorService es = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 10; i++) {
            es.submit( () -> {
                LOGGER.info(Thread.currentThread().getName() + " submit");
            });
        }

        for (int i = 0; i < 10; i++) {
            es.execute( () -> {
                LOGGER.info(Thread.currentThread().getName() + " execute");
            });
        }

        es.shutdown();
    }

    /**
     * 无界线程池
     */
    private static void cacheThreadPoolTest() {

        ExecutorService es = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            es.submit( () -> {
                LOGGER.info(Thread.currentThread().getName() + " submit");
            });
        }

        for (int i = 0; i < 10; i++) {
            es.execute( () -> {
                LOGGER.info(Thread.currentThread().getName() + " execute");
            });
        }

    }
}
