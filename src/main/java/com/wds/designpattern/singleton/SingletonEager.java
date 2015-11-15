package com.wds.designpattern.singleton;

/**
 * 饿汉式单例
 * Created by wds on 2015/11/15.
 */
public class SingletonEager {
    private static final SingletonEager singleton = new SingletonEager();

    private SingletonEager() {
        //不做任何操作
    }

    public static SingletonEager getSingleton() {
        return singleton;
    }
}
