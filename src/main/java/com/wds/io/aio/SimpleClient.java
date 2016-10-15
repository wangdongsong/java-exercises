package com.wds.io.aio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousByteChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by wangdongsong1229@163.com on  2015-08-22.
 */
public class SimpleClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleClient.class);

    private AsynchronousSocketChannel client;

    public SimpleClient(String host, int port) {
        try {
            this.client = AsynchronousSocketChannel.open();
            Future<?> future = client.connect(new InetSocketAddress(host, port));
            future.get();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (ExecutionException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void write(byte b) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(32);
        System.out.println(byteBuffer);
        byteBuffer.put(b);
        byteBuffer.flip();
        System.out.println(byteBuffer);
        client.write(byteBuffer);
    }
}
