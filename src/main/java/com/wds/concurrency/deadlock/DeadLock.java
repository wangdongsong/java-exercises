package com.wds.concurrency.deadlock;

/**
 * Created by wangdongsong on 15-8-8.
 */
public class DeadLock {

    public static void main(String[] args) {

        Friend me = new Friend("me");

        Friend you = new Friend("you");

        new Thread(new Runnable() {
            @Override
            public void run() {
                me.bow(you);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                you.bow(me);
            }
        }).start();



    }

}
