package com.wds.concurrency.security.lock;

/**
 * Created by wds on 2015/10/18.
 */
public class LockTest {

    public static void main(String[] args) {
        final Count count = new Count();
        for (int i = 0; i <3; i++) {
            Thread t = new Thread(){
                @Override
                public void run() {
                    count.get();
                }
            };
            t.start();
        }

        for (int i = 0; i <3; i++) {
            Thread t = new Thread(){
                @Override
                public void run() {
                    count.put();
                }
            };
            t.start();
        }
    }

}
