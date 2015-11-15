package com.wds.designpattern.singleton;

/**
 * double check
 * Created by wds on 2015/11/15.
 */
public class SingletonLazyDoubleCheck {
    private static SingletonLazyDoubleCheck singleton = null;

    private SingletonLazyDoubleCheck() {
        //不做任何操作
    }

    public static SingletonLazyDoubleCheck getSingleton() {
        if (singleton == null) {
            synchronized (SingletonLazyDoubleCheck.class) {
                singleton = new SingletonLazyDoubleCheck();
            }
        }
        return singleton;
    }
}
