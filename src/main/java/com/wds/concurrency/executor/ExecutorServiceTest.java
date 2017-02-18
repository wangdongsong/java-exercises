package com.wds.concurrency.executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.SyslogAppender;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * invokeAny&invokeAll等方法的使用练习
 * Created by wangdongsong1229@163.com on 2017/2/16.
 */
public class ExecutorServiceTest {
    private static final Logger LOGGER = LogManager.getLogger(ExecutorServiceTest.class);

    public static void main(String[] args) {

        //invokeAnyTest();

        //invokeAnyExceptionTest();

        invokeAllTest();

    }

    /**
     * invokeAll会对Callable抛出去的异常是可以处理的
     *
     * invokeAny方法而某一个任务正确的结束返回值，则Callable抛出去的异常在main()方法中不会处理处理，当所有的任务都没有正确返回
     * 返回值时，则说明最后Callable抛出的异常在main方法中处理
     */
    private static void invokeAllTest() {
        List list = new ArrayList<Callable>();
        list.add(new CallableA());

        list.add(new CallableExcption());

        ExecutorService es = Executors.newCachedThreadPool();

        try {
            List getValueAs = es.invokeAll(list);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }

        es.shutdown();
    }

    private static void invokeAnyExceptionTest() {
        List list = new ArrayList<Callable>();
        list.add(new CallableA());

        list.add(new CallableExcption());

        ExecutorService es = Executors.newCachedThreadPool();
        try {
            String getValueA = (String) es.invokeAny(list);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (ExecutionException e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info("子线程异常之后并未执行主线程执行");

        es.shutdown();
    }

    /**
     * invokeAny有阻塞特性
     * <p>
     * 该方法是取得第一个完成任务的结果值，当第一个任务执行害怕后，会调用interrupt()方法将其它任务中断
     * 所以在这些任务中可以结合<code>if(Thread.currentThread().isInterrupted()==true)</code>代码来决定任务是否继续执行
     * <p>
     * 在无<code>if(Thread.currentThread().isInterrupted()==true)</code>情况下，已经获得第一个运行的结果值后，其它线程继续执行
     * <p>
     * 在有<code>if(Thread.currentThread().isInterrupted()==true)</code>情况下，已经获得第一个运行的结果值后，其它线程如果使用
     * <code>throw new InterruptedException</code>，虽然抛出异常，但在main线程中不能捕获异常，如果想捕获，则在Callable中使用try-catch捕获
     */
    private static void invokeAnyTest() {

        List list = new ArrayList<Callable>();
        list.add(new CallableA());
        /*
         * CallableB中无中断，由CallableA执行结束之后，CallableB中继续执行，然Callable2则会中断
         */
        //list.add(new CallableB());
        list.add(new CallableB2());

        ExecutorService es = Executors.newCachedThreadPool();
        try {
            String getValueA = (String) es.invokeAny(list);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (ExecutionException e) {
            LOGGER.error(e.getMessage(), e);
        }
        es.shutdown();
    }

    private static class CallableA implements Callable<String> {
        @Override
        public String call() throws Exception {
            LOGGER.info("CallableA begin " + System.currentTimeMillis());
            for (int i = 0; i < 2; i++) {
                Math.random();
                Math.random();
                Math.random();
                LOGGER.info("CallableA " + (i + 1));
            }
            LOGGER.info("CallabeA end" + System.currentTimeMillis());
            return "returnA";
        }
    }

    private static class CallableB implements Callable<String> {
        @Override
        public String call() throws Exception {
            LOGGER.info("CallableB begin " + System.currentTimeMillis());
            for (int i = 0; i < 10; i++) {
                Math.random();
                Math.random();
                Math.random();
                LOGGER.info("CallableB " + (i + 1));
            }
            LOGGER.info("CallabeB end" + System.currentTimeMillis());
            return "returnA";
        }
    }

    /**
     * 若不增加try-catch语句，则NullPointerException不会被捕获
     *
     * 需要手动添加try-catch语句以捕获执行过程中遇到的异常
     */
    private static class CallableExcption implements Callable<String> {
        @Override
        public String call() throws Exception {
            try {
                new CallableB().call();
                if (true) {
                    throw new NullPointerException();
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
            return "returnException";
        }
    }

    private static class CallableB2 implements Callable<String> {
        @Override
        public String call() throws Exception {
            LOGGER.info("CallableB2 begin " + System.currentTimeMillis());
            for (int i = 0; i < 323456; i++) {
                if (Thread.currentThread().isInterrupted() == false) {
                    Math.random();
                    Math.random();
                    Math.random();
                    LOGGER.info("CallableB2 " + (i + 1));
                } else {
                    LOGGER.error("********异常中断！");
                    throw new InterruptedException();
                }
            }
            LOGGER.info("CallabeB2 end" + System.currentTimeMillis());
            return "returnA";
        }
    }

}
