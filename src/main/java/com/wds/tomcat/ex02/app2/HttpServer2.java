package com.wds.tomcat.ex02.app2;

import com.wds.tomcat.ex02.app1.Request;
import com.wds.tomcat.ex02.app1.Response;
import com.wds.tomcat.ex02.app1.ServletProcessor;
import com.wds.tomcat.ex02.app1.StaticResourceProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by wangdongsong1229@163.com on 2018/1/8.
 */
public class HttpServer2 {

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer2 server = new HttpServer2();
        server.await();
    }

    private void await() {
        ServerSocket serverSocket = null;
        int port = 8080;

        try {
            serverSocket = new ServerSocket(port, 1, Inet4Address.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        while (!shutdown) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;

            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                Request request = new Request(input);
                request.parse();

                Response response = new Response(output);
                response.setRequest(request);

                // 检查请求是否为Servlet
                if (request.getUri().startsWith("/servlet/")) {
                    ServletProcessor2 processor = new ServletProcessor2();
                    processor.process(request, response);
                } else {
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request, response);
                }

                socket.close();

                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
