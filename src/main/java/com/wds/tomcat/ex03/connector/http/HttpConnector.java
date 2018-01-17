package com.wds.tomcat.ex03.connector.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Http连接器
 *
 * 实现Runnable接口，使用自己的线程
 *
 * Created by wangdongsong1229@163.com on 2018/1/15.
 */
public class HttpConnector implements Runnable {
    boolean stopped;
    private String scheme = "http";

    /**
     * 主要作用：
     * 等待HTTP请求
     * 为每一个请求创建一个HttpProcessor实例
     * 调用HttpProcessor对象的process方法
     */
    @Override
    public void run() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        while (!stopped) {
            Socket socket = null;

            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                continue;
            }

            HttpProcessor processor = new HttpProcessor(this);
            processor.process(socket);
        }
    }

    /**
     * 启动
     */
    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     *
     * @return 请求协议 ，例如Http协议
     */
    public String getScheme() {
        return scheme;
    }
}
