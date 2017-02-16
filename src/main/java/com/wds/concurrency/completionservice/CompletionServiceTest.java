package com.wds.concurrency.completionservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * CompletionService接口可以解决Future的缺点
 *
 * CompletionService中维护了一个阻塞队列，当任务完成之后放到该队列中，获取结果时，需要将结果从队列中清除，不然会引起内存
 * 溢出
 *
 * Created by wangdongsong1229@163.com on 2017/2/15.
 */
public class CompletionServiceTest {
    private static final Logger LOGGER = LogManager.getLogger(CompletionServiceTest.class);

    public static void main(String[] args) {

        baseCompletionTest();

    }

    private static void baseCompletionTest() {

        List<Callable> list = new ArrayList<>();
        TestCallable tc = null;
        for (int i = 1; i < 6; i++) {
            tc = new TestCallable("username" + i, i * 1000);
            list.add(tc);
        }

        ThreadPoolExecutor tpe = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        CompletionService cs = new ExecutorCompletionService(tpe);

        list.forEach((task) -> cs.submit(task));

        for (int i = 1; i < 6; i++) {
            //乱序打印，谁先完成先打印谁
            try {
                LOGGER.info(cs.take().get());
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            } catch (ExecutionException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        //poll
        list.forEach((task) -> cs.submit(task));
        for (int i = 1; i < 6; i++) {
            //乱序打印，谁先完成先打印谁
            LOGGER.info(cs.poll());
        }
        tpe.shutdown();

    }

    private static class TestCallable implements Callable<String> {

        private String userName;
        private long sleepValue;

        public TestCallable(String userName, long sleepValue) {
            this.userName = userName;
            this.sleepValue = sleepValue;
        }

        @Override
        public String call() throws Exception {
            LOGGER.info(this.userName);
            Thread.sleep(this.sleepValue);
            return "return = " + this.userName;
        }
    }


}
