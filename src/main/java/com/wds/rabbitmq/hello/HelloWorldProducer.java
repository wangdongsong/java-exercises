package com.wds.rabbitmq.hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by wds on 2016/2/22.
 */
public class HelloWorldProducer {

    private static final Logger LOGGER = LogManager.getLogger(HelloWorldProducer.class);
    private static final String host = "192.168.254.205";
    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);

        factory.setPort(5673);
        factory.setUsername("wds");
        factory.setPassword("wds");
        factory.setVirtualHost("wdsVhost");

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
