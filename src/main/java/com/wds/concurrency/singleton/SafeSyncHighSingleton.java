package com.wds.concurrency.singleton;

/**
 * Created by wds on 2015/10/23.
 */
public class SafeSyncHighSingleton {
    private static SafeSyncHighSingleton singleton;
    private static String lock = new String("Lock");

    private SafeSyncHighSingleton() {

    }

    public static SafeSyncHighSingleton getInstance() {
        if (singleton == null) {
            synchronized (lock) {
                if (singleton == null) {
                    singleton = new SafeSyncHighSingleton();
                }
            }
        }

        return singleton;
    }

}
