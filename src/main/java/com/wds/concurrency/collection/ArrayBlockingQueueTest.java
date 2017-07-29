package com.wds.concurrency.collection;

import java.util.concurrent.*;

/**
 * Created by wangdongsong1229@163.com on 2017/7/28.
 */
public class ArrayBlockingQueueTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        BlockingQueue<Future> queue = new ArrayBlockingQueue<Future>(10);
        Executor executor = Executors.newFixedThreadPool(3);
        CompletionService cs = new ExecutorCompletionService(executor, queue);
        CallTak task = null;
        for (int i = 10; i > 1; i--) {
             task = new CallTak(new Long(i));
            //Future future =
            cs.submit(task);
            //String result = (String)future.get();
            //queue.take();
            //System.out.println(result);
        }

        for(int i = 10; i > 1; i--){
            Future<String> result = cs.take();
            System.out.println(result.get());
        }


        System.out.println(queue.size());

    }

    static class CallTak implements Callable<String>{

        private long sleepTime;

        public CallTak(long sleepTime) {
            this.sleepTime = sleepTime;
        }

        @Override
        public String call() throws Exception {
            Thread.sleep(sleepTime * 100);

            return "result-" + sleepTime;
        }
    }

}
