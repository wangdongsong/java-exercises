package com.wds.concurrency.lock;

import java.util.Random;

/**
 * Created by wangdongsong on 15-8-9.
 */
public class BowLoop implements Runnable{
    private Friend bower;
    private Friend bowee;

    public BowLoop(Friend bower, Friend bowee) {
        this.bowee = bowee;
        this.bower = bower;
    }


    @Override
    public void run() {
        Random random = new Random();

        for (; ;) {
            try {
                Thread.sleep(random.nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            bowee.bow(bower);
        }
    }
}
