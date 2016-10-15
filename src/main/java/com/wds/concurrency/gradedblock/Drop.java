package com.wds.concurrency.gradedblock;

/**
 * Created by wangdongsong1229@163.com on  15-8-8.
 */
public class Drop {

    /**
     * Message sent from producer to consumer
     */
    private String message;

    private boolean empty = true;

    public synchronized String take() {
        while (empty) {
            //System.out.println("take");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        empty = true;
        notifyAll();

        return message;
    }

    public synchronized void put(String msg) {
        //System.out.println(empty);
        while (!empty) {
            System.out.println("put");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        empty = false;
        this.message = msg;
        notifyAll();
        //System.out.println("put2");
    }
}
