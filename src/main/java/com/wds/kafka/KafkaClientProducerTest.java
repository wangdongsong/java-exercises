package com.wds.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

/**
 * Created by wangdongsong1229@163.com on 2017/2/28.
 */
public class KafkaClientProducerTest {
    private static final Logger LOGGER = LogManager.getLogger(KafkaClientProducerTest.class);

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.254.202:9092");
        props.put("acks", "1");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for(int i = 0; i < 10; i++){
            producer.send(new ProducerRecord<String, String>("testReplicatedTopic", Integer.toString(i), Integer.toString(i) + "wds"));
        }

        producer.close();
    }
}
