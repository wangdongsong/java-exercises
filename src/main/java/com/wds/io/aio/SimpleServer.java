package com.wds.io.aio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

/**
 * Created by WANGDONGSONG846 on 2015-08-22.
 */
public class SimpleServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleServer.class);

    public SimpleServer(int port) {
        try {
            AsynchronousServerSocketChannel listener = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(port));
            listener.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {

                @Override
                public void completed(AsynchronousSocketChannel channel, Void attachment) {
                    listener.accept(null, this);
                    handle(channel);
                }

                @Override
                public void failed(Throwable exc, Void attachment) {
                    //LOGGER.error(exc.getStackTrace().toString());
                }
            });
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    private void handle(AsynchronousSocketChannel channel) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(32);
        try {
            channel.read(byteBuffer).get();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (ExecutionException e) {
            LOGGER.error(e.getMessage(), e);
        }
        byteBuffer.flip();
        System.out.println(byteBuffer.get());
    }

}
