package com.wds.java8InAction.ch05.exercise;

/**
 * Created by wangdongsong1229@163.com on 2017/8/20.
 */
public class Trader {
    private final String name;
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }
}
