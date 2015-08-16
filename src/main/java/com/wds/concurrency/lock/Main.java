package com.wds.concurrency.lock;

/**
 * Created by wangdongsong on 15-8-9.
 */
public class Main {


    public static void main(String[] args) {
        final Friend me = new Friend("Me");

        final Friend your = new Friend("Your");


        new Thread(new BowLoop(me, your)).start();

        new Thread(new BowLoop(your, me)).start();
    }
}
