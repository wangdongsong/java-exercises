package com.wds.tomcat.ex02.app1;

import javax.servlet.ServletOutputStream;
import java.io.*;
import java.util.Locale;

/**
 * Created by wangdongsong1229@163.com on 2018/1/9.
 */
public class Response {
    private static final int BUFFER_SIZE = 1048;
    Request request;
    OutputStream output;
    PrintWriter writer;

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;

        try {

            File file = new File(Constants.WEB_ROOT, request.getUri());
            fis = new FileInputStream(file);

            int ch = fis.read(bytes, 0, BUFFER_SIZE);
            while (ch != -1) {
                output.write(bytes, 0, ch);
                ch = fis.read(bytes, 0, BUFFER_SIZE);
            }
        } catch (FileNotFoundException ex) {
            String errorMessage = "HTTP/1.1 404 File not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 23\r\n" +
                    "\r\n" +
                    "<h1>File not found</h1>";
            output.write(errorMessage.getBytes());
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }

    public void flushBuffer() throws IOException {

    }

    public int getBufferSize() {
        return 0;
    }

    public String getCharacterEncoding() {
        return null;
    }

    public Locale getLocale() {
        return null;
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    public PrintWriter getWriter() throws IOException {
        writer = new PrintWriter(output, true);
        return writer;
    }

    public boolean isCommited() {
        return false;
    }

    public void reset() {

    }

    public void resetBuffer() {

    }

    public void setBufferSize(int size) {

    }

    public void setContentLength(int length) {

    }

    public void setContentType(String type) {

    }

    public void setLocale(Locale locale) {

    }












    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
