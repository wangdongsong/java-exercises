package com.wds.java8InAction.ch02;

import com.wds.java8InAction.ch01.Apple;

/**
 * Created by wangdongsong1229@163.com on 2017/8/16.
 */
public class AppleColorPredicate implements ApplePrediccate {

    @Override
    public boolean test(Apple apple) {
        return "green".equalsIgnoreCase(apple.getColor());
    }
}
