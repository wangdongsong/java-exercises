package com.wds.concurrency.base.gradedblock;

/**
 * Created by wangdongsong on 15-8-8.
 */
public class MainTest {

    public static void main(String[] args) {
        Drop drop = new Drop();

        new Thread(new Producer(drop)).start();
        new Thread(new Consumer(drop)).start();
    }
}
