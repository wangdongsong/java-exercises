package com.wds.rabbitmq;

/**
 * Created by wangdongsong1229@163.com on  2016/8/31.
 */
public class RabbitMQBaseInfo {

    public static final String VHOST_NAME = "wdsVhost";
    public static final String HOST_NAME = "192.168.254.205";
    public static final String PORT = "5673";
    public static final String USER_NAME = "wds";
    public static final String PASSWORD = "wds";

    public static final String RABBITMQ_URL = "amqp://" + USER_NAME + ":" + PASSWORD + "@" + HOST_NAME + ":" + PORT + "/" + VHOST_NAME;


}
