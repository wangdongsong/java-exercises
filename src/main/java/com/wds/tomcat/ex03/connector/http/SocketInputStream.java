package com.wds.tomcat.ex03.connector.http;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wangdongsong1229@163.com on 2018/1/17.
 */
public class SocketInputStream extends InputStream {

    public SocketInputStream(InputStream inputStream, int i) {

    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    public void readRequestLine(HttpRequestLine requestLine) {
    }

    public void readHeader(HttpHeader header) {
    }
}
