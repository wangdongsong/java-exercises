package com.wds.designpattern.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * 1、序列化
 * 2、clonable
 * 3、反射
 * 4、多个classloader
 * Created by wds on 2015/11/15.
 */
public class Singleton implements Serializable {
    private static Singleton singleton = new Singleton();

    private Singleton() {
        if (singleton != null) {
            throw new IllegalStateException("Aleady created");
        }
    }

    public static Singleton getSingleton() {
        return singleton;
    }

    private Object readResolve() throws ObjectStreamException {
        return singleton;
    }

    private Object writeReplace() throws ObjectStreamException {
        return singleton;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Singleton, cannot be clonned");
    }

    private static Class getClass(String className) throws ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = Singleton.class.getClassLoader();
        }
        return (classLoader.loadClass(className));
    }
}
