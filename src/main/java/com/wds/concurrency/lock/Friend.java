package com.wds.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangdongsong1229@163.com on  15-8-9.
 */
public class Friend {

    private final String name;

    private final Lock lock = new ReentrantLock();

    public Friend(String name) {
        this.name = name;
    }

    public boolean impendingBow(Friend bower) {
        Boolean myLock = false;
        Boolean yourLock = false;

        try {
            myLock = lock.tryLock();
            yourLock = bower.lock.tryLock();
        } finally {
            if (!(myLock && yourLock)) {
                if (myLock) {
                    lock.unlock();
                }
                if (yourLock) {
                    bower.lock.unlock();
                }
            }
        }

        return myLock && yourLock;
    }

    public void bow(Friend bower) {
        if (impendingBow(bower)) {
            try {
                System.out.format("%s: %s has " + " bowed to me! %n", this.name, bower.getName());
                bower.bowBack(this);
            } finally {
                lock.unlock();
                bower.lock.unlock();
            }
        } else {
            System.out.format("%s: %s started" + " to bow to me, but saw that" + " I was already bowing to " + " him.%n", this.name, bower.getName());
        }

    }

    private void bowBack(Friend friend) {

        System.out.format("%s: %s has" + " bowed back to me!%n", this.name, friend.getName());

    }

    public String getName() {
        return name;
    }
}
