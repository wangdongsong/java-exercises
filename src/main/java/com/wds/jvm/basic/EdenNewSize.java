package com.wds.jvm.basic;

/**
 * 分别使用4种参数执行本程序
 * 1、-Xmx20m -Xmx20m -Xmn1m -XX:SurvivorRatio=2 -XX:+PrintGCDetails
 * 1、-Xmx20m -Xmx20m -Xmn7m -XX:SurvivorRatio=2 -XX:+PrintGCDetails
 * 3、-Xmx20m -Xmx20m -Xmn15m -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * 4、-Xmx20m -Xmx20m -XX:NewRatio=2 -XX:+PrintGCDetails
 * Created by wds on 2015/11/16.
 */
public class EdenNewSize {

    public static void main(String[] args) {
        byte[] b = null;
        for (int i = 0; i < 10; i++) {
            b = new byte[1 * 1024 * 1024];
        }
    }

}
