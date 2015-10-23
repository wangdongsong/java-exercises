package com.wds.concurrency.readwritelock;

/**
 * Created by wds on 2015/10/18.
 */
public class TestReadWriteLock {

    public static void main(String[] args) {
        final Count count = new Count();
        for (int i = 0; i < 3; i++) {

            //Lambda
            new Thread(() -> count.get()).start();

            new Thread(){
                @Override
                public void run() {
                    count.get();
                }
            }.start();

        }

        for (int i = 0; i < 3; i++) {

            new Thread(() -> count.put()).start();

            new Thread(){
                @Override
                public void run() {
                    count.put();
                }
            }.run();

        }
    }
}
