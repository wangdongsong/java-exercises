package com.wds.concurrency.singleton;

/**
 * 线程不安全的单例
 * Created by wds on 2015/10/23.
 */
public class UnSafeSingleton {

    private static UnSafeSingleton singleton;

    private UnSafeSingleton() {
    }

    public static UnSafeSingleton getInstance() {
        if (singleton == null) {
            singleton = new UnSafeSingleton();
        }

        return singleton;
    }
}
