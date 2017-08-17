package com.wds.java8InAction.ch03;

/** ch03 Lambda表达式
 * Created by wangdongsong1229@163.com on 2017/8/18.
 */
public class Test {

    public static void main(String[] args) {
        Runnable r = () -> System.out.println("HelloWorld2");
        r.run();
    }

}
