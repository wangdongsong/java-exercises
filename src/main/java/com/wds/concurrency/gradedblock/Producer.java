package com.wds.concurrency.gradedblock;

import java.util.Random;

/**
 * Created by wangdongsong on 15-8-8.
 */
public class Producer implements Runnable {

    private Drop drop;

    public Producer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        String info[] = {"you", "me", "and", "her"};
        Random random = new Random();
        //System.out.println("producer");
        for (int i = 0; i < info.length; i++) {
            drop.put(info[i]);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        drop.put("DONE");
    }
}
