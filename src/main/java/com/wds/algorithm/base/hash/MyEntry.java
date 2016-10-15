package com.wds.algorithm.base.hash;

/**
 * Created by wangdongsong1229@163.com on  2016/9/25.
 */
public class MyEntry {

    int key;
    int value;
    MyEntry next;

    public MyEntry(int key, int value, MyEntry next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
