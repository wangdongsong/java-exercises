package com.wds.rabbitmq.fanout_demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.wds.rabbitmq.RabbitMQBaseInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

/**
 * Created by wangdongsong1229@163.com on  2016/8/31.
 */
public class FanoutProducer {

    private static final Logger LOGGER = LogManager.getLogger(FanoutProducer.class);
    private static final String ERROR_QUEUE_NAME = "topicQueueError";
    private static final String INFO_QUEUE_NAME = "topicQueueInfo";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        try {
            factory.setUri(RabbitMQBaseInfo.RABBITMQ_URL);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare("uploadExchange", "fanout");

            channel.basicPublish("uploadExchange", "Hello", null, "add points".getBytes());

            channel.close();
            connection.close();
        } catch (URISyntaxException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (KeyManagementException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (TimeoutException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

}
