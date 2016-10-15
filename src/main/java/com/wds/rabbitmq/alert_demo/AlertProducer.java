package com.wds.rabbitmq.alert_demo;

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
public class AlertProducer {

    private static final Logger LOGGER = LogManager.getLogger(AlertProducer.class);
    private static final String ERROR_QUEUE_NAME = "topicQueueError";
    private static final String INFO_QUEUE_NAME = "topicQueueInfo";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        try {
            factory.setUri(RabbitMQBaseInfo.RABBITMQ_URL);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare("wdsExchange", "topic");
            channel.queueDeclare(ERROR_QUEUE_NAME,false, false, true, null );
            channel.queueDeclare(INFO_QUEUE_NAME, false, false, true, null);
            channel.queueBind(ERROR_QUEUE_NAME, "wdsExchange", "error*");
            channel.queueBind(INFO_QUEUE_NAME, "wdsExchange", "info*");

            channel.basicPublish("wdsExchange", "error*", null, "ErrorHelloWorld".getBytes());

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
