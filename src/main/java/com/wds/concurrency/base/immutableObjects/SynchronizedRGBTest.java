package com.wds.concurrency.base.immutableObjects;

/**
 * Created by wangdongsong on 15-8-9.
 */
public class SynchronizedRGBTest {
    public static void main(String[] args) {

        /*
         * 下面的代码有可能导致不一致
         * 线程1执行到Statement 1之后
         * 线程2在Statement 1之后、Statement2之前调用了color.set()方法
         * 则线程1取得name就是不一致的
         */
        SynchronizedRGB color = new SynchronizedRGB(0, 0, 0, "Black");
        int myColorInt = color.getRGB(); //Satement 1
        String myColorName = color.getName(); //Statement 2

    }
}
