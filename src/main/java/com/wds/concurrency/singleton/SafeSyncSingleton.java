package com.wds.concurrency.singleton;

/**
 * 线程安全，但并发性不高
 * Created by wds on 2015/10/23.
 */
public class SafeSyncSingleton {
    private static SafeSyncSingleton singleton;

    private SafeSyncSingleton() {

    }

    public static synchronized SafeSyncSingleton getInstance() {
        if (singleton == null) {
            singleton = new SafeSyncSingleton();
        }

        return singleton;
    }
}
