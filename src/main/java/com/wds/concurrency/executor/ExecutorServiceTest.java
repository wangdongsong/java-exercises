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

        invokeAnyTest();

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
    }

    private static class CallableA implements Callable<String> {
        @Override
        public String call() throws Exception {
            LOGGER.info("CallableA begin " + System.currentTimeMillis());
            for (int i = 0; i < 12345; i++) {
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
            for (int i = 0; i < 223456; i++) {
                Math.random();
                Math.random();
                Math.random();
                LOGGER.info("CallableB " + (i + 1));
            }
            LOGGER.info("CallabeB end" + System.currentTimeMillis());
            return "returnA";
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
