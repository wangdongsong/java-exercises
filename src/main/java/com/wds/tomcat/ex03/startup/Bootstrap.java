package com.wds.tomcat.ex03.startup;

import com.wds.tomcat.ex03.connector.http.HttpConnector;

/**
 * 启用类
 * Created by wangdongsong1229@163.com on 2018/1/15.
 */
public class Bootstrap {

    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        connector.start();
    }

}
