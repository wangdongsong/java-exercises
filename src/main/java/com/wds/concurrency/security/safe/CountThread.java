package com.wds.concurrency.security.safe;

/**
 * Created by wds on 2015/10/18.
 */
public class CountThread extends Thread {
    private Count count;

    public CountThread( Count count) {
        this.count = count;
    }

    @Override
    public void run() {
        count.add();
    }
}
