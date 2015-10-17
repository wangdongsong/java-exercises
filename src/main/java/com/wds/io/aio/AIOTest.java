package com.wds.io.aio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangdongsong on 2015-08-22.
 */
public class AIOTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AIOTest.class);
    public static final int PORT = 9000;
    public static final String LOCALHOST = "localhost";

    public void startServer() {
        SimpleServer server = new SimpleServer(PORT);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void startClient() {
        SimpleClient client = new SimpleClient(LOCALHOST, PORT);
        client.write((byte)11);
    }

    public static void main(String[] args) {
        AIOTest test = new AIOTest();
        test.startServer();
        //test.startClient();
    }
}
