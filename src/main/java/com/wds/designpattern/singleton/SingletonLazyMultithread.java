package com.wds.designpattern.singleton;

/**
 * 线程安全的饿汉式
 * Created by wds on 2015/11/15.
 */
public class SingletonLazyMultithread {
    private static SingletonLazyMultithread singleton = null;

    private SingletonLazyMultithread() {
        //不做任何操作
    }

    public static synchronized SingletonLazyMultithread getSingleton() {
        if (singleton == null) {
            singleton = new SingletonLazyMultithread();
        }
        return singleton;
    }
}
