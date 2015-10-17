package com.wds.concurrency.gradedblock;

import java.util.Random;

/**
 * Created by wangdongsong on 15-8-8.
 */
public class Consumer implements Runnable {

    private Drop drop;

    public Consumer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {

        Random random = new Random();

        for (String msg = drop.take(); !msg.equals("DONE"); msg = drop.take()) {
            System.out.format("Message received: %s%n", msg);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
