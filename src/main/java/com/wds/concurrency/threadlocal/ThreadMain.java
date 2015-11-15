package com.wds.concurrency.threadlocal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by wds on 2015/10/18.
 */
public class ThreadMain {

    private static final Logger LOGGER = LogManager.getLogger(ThreadMain.class);

    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public static void main(String[] args) {
        ThreadMain main = new ThreadMain();
        TestClient t1 = new TestClient(main);
        TestClient t2 = new TestClient(main);
        TestClient t3 = new TestClient(main);

        t1.start();
        t2.start();
        t3.start();
    }

    public ThreadLocal<Integer> getThreadLocal() {
        return seqNum;
    }

    public int getNextNum() {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }


}
