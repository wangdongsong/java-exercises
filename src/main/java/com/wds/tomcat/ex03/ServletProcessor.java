package com.wds.tomcat.ex03;

import com.wds.tomcat.ex03.connector.http.*;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Created by wangdongsong1229@163.com on 2018/1/19.
 */
public class ServletProcessor {

    public void process(HttpRequest request, HttpResponse response) {
        String uri = request.getRequestURI();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;

        try {
            //创建类装载器
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);
            //从org.apache.catalina.startup.ClassLoaderFactory中学习创建资源仓库
            String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
            System.out.println(repository);
            urls[0] = new URL(null, repository, streamHandler);
            //创建类加载器
            loader = new URLClassLoader(urls);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Class myClass = null;
        try {
            //加载Servlet
            myClass = loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Servlet servlet = null;
            HttpRequestFacade requestFacade = new HttpRequestFacade(request);
            HttpResponseFacade responseFacade = new HttpResponseFacade(response);
            servlet = (Servlet) myClass.newInstance();
            servlet.service(requestFacade, responseFacade);
            ((HttpResponse)response).fiishResponse();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
