package com.wds.rabbitmq.hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by wds on 2016/2/22.
 */
public class HelloWorldProducer {

    private static final Logger LOGGER = LogManager.getLogger(HelloWorldProducer.class);
    private static final String host = "192.168.254.215";
    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);

        factory.setPort(5672);
        factory.setUsername("wds");
        factory.setPassword("wds");
        factory.setVirtualHost("wds");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        LOGGER.info("[x] Sent" + message + "");

        channel.close();
        connection.close();

    }

}
