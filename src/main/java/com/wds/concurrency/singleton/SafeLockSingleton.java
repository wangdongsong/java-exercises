package com.wds.concurrency.singleton;

import com.wds.concurrency.security.lock.LockTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wds on 2015/10/23.
 */
public class SafeLockSingleton {
    private static SafeLockSingleton singleton;
    private static Lock lock = new ReentrantLock();

    private SafeLockSingleton() {

    }

    public static SafeLockSingleton getSingleton() {
        if (singleton == null) {
            lock.lock();
            if (singleton == null) {
                singleton = new SafeLockSingleton();
            }
        }
        return singleton;
    }
}
