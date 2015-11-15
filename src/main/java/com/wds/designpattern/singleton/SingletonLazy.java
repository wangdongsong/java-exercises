package com.wds.designpattern.singleton;

/**
 * Lazy模式，线程不安全
 * Created by wds on 2015/11/15.
 */
public class SingletonLazy {
    private static SingletonLazy singleton = null;

    private SingletonLazy() {
        //不做任何操作
    }

    /**
     * 非线程安全
     * @return 单例对象
     */
    public static SingletonLazy getSingleton() {
        if (singleton == null) {
            singleton = new SingletonLazy();
        }
        return singleton;
    }
}
