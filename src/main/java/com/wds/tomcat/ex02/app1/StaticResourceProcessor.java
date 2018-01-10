package com.wds.tomcat.ex02.app1;

import java.io.IOException;

/**
 * Created by wangdongsong1229@163.com on 2018/1/9.
 */
public class StaticResourceProcessor {

    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
