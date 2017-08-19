package com.wds.java8InAction.ch03;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 函数式接口
 * Created by wangdongsong1229@163.com on 2017/8/19.
 */
@FunctionalInterface
public interface BufferedReaderProcessor {
    String process(BufferedReader br) throws IOException;
}
