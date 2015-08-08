package com.wds.concurrency.base.deadlock;

/**
 * Created by wangdongsong on 15-8-8.
 */
public class Friend {

    private final String name;

    public String getName() {
        return name;
    }

    public Friend(String name) {
        this.name = name;
    }

    public synchronized void bow(Friend bower) {
        System.out.format("%s: %s has bowed to me!%n", this.getName(), bower.getName());
        bower.bowBack(this);
    }

    private synchronized void bowBack(Friend bower) {
        System.out.format("%s: %s has bowed back to me!%n", this.getName(), bower.getName());

    }


}
