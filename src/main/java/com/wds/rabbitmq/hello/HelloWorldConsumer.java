package com.wds.rabbitmq.hello;

import com.rabbitmq.client.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by wds on 2016/2/22.
 */
public class HelloWorldConsumer {
    private static final Logger LOGGER = LogManager.getLogger(HelloWorldConsumer.class);
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
        //channel.queueBind(QUEUE_NAME, "amq.rabbitmq.log", "info");

        LOGGER.info("[*] Waiting for message. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                String message = new String(body, "UTF-8");

                LOGGER.info("[x] Received" + message);

            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

}
