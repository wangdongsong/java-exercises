package com.wds.rabbitmq.fanout_demo;

import com.rabbitmq.client.*;
import com.wds.rabbitmq.RabbitMQBaseInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

/**
 * Created by wangdongsong on 2016/9/1.
 */
public class FanoutConsumer {
    private static final Logger LOGGER = LogManager.getLogger(FanoutConsumer.class);

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        try {
            factory.setUri(RabbitMQBaseInfo.RABBITMQ_URL);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            String topicQueueError;
            topicQueueError = channel.basicConsume("topicQueueError", false, new DefaultConsumer(channel){
                @Override
                public void handleConsumeOk(String consumerTag) {
                    super.handleConsumeOk(consumerTag);
                }

                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    LOGGER.info(consumerTag);
                    LOGGER.info(envelope.getRoutingKey());
                    LOGGER.info(new String(body, "UTF-8"));
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            });

            //channel.close();
            //connection.close();
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
