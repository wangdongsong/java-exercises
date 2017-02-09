package com.wds.concurrency.juc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Phaser;

/**
 * Created by wangdongsong1229@163.com on 2017/2/9.
 */
public class PhaserAdvancedTest {

    private static final Logger LOGGER = LogManager.getLogger(PhaserAdvancedTest.class);

    public static void main(String[] args) {

        //advanceBaseTest();

        registerBaseTest();

    }

    /**
     * getRegisteredParties获取注册的parties数量
     * register动态的增加一个parties数量
     */
    private static void registerBaseTest() {
        Phaser p = new Phaser(5);
        LOGGER.info("init parties=" + p.getRegisteredParties());

        //单个增加
        p.register();
        LOGGER.info("add parties=" + p.getRegisteredParties());

        p.bulkRegister(10);
        LOGGER.info("buld parties=" + p.getRegisteredParties());

    }

    private static void advanceBaseTest() {
        Phaser p = new Phaser(3){
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                LOGGER.info("method onAdvance is called");
                //返回true不等待了，Phaser呈无效&销毁状态
                //返回false则Phaser继续工作
                return true;
            }
        };

        new Thread(() -> {advanceTest(p);}).start();
        new Thread(() -> {advanceTest(p);}).start();
        new Thread(() -> {advanceTest(p);}).start();
    }

    private static void advanceTest(Phaser p) {
        LOGGER.info(Thread.currentThread().getName() + " A begin");
        p.arriveAndAwaitAdvance();
        LOGGER.info(Thread.currentThread().getName() + " A end");
        p.arriveAndAwaitAdvance();
    }
}
