package com.wds.concurrency.juc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch的功能是判断计数不为0时则当前线程呈wait状态，
 * CountDownLatch是做减法操作，计数无法被重置，如果计数想重置请考虑使用CyclicBarrier类
 * Created by wds on 2015/10/25.
 */
public class CountDownLatchTest {
    private static final Logger LOGGER = LogManager.getLogger(CountDownLatchTest.class);

    public static void main(String[] args) throws InterruptedException {

        //基本练习
        //baseTest();

        //比赛测试
        runningTest();

    }

    private static void runningTest() throws InterruptedException {

        CountDownLatch comingTag = new CountDownLatch(10);   //裁判等待所有运动员到来
        CountDownLatch waitTag = new CountDownLatch(1);    //等待裁判说准备开始
        CountDownLatch waitRunTag = new CountDownLatch(10);  //等待起跑
        CountDownLatch beginTag = new CountDownLatch(1);    //起跑
        CountDownLatch endTag = new CountDownLatch(10);  //所有运动员到达终点



        for(int i = 0; i < 10; i++) {
            new Thread(() -> {
                LOGGER.info("运动员使用不同交通工具不同速度到达起跑点，正向这头走！");
                try {
                    Thread.sleep((int) (Math.random() * 1000));
                    LOGGER.info(Thread.currentThread().getName() + "到起跑点了");

                    comingTag.countDown();
                    LOGGER.info("等待裁判说准备！");

                    waitTag.await();

                    LOGGER.info("各就各位，准备起跑姿势！");

                    Thread.sleep((int) (Math.random() * 1000));

                    waitRunTag.countDown();
                    beginTag.await();

                    LOGGER.info(Thread.currentThread().getName() + "运动员起跑并且用时不确定");
                    Thread.sleep((int) (Math.random() * 1000));

                    endTag.countDown();

                    LOGGER.info(Thread.currentThread().getName() + "运动员到达终点！");
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage(), e);
                }

            }).start();


        }

        LOGGER.info("裁判员等运动员到场");

        comingTag.await();

        LOGGER.info("运动员已到位，等待5秒发指令");

        Thread.sleep(5000);
        waitTag.countDown();
        LOGGER.info("各就各位");

        waitRunTag.await();
        Thread.sleep(2000);

        LOGGER.info("发令枪响");
        beginTag.countDown();
        endTag.await();
        LOGGER.info("所有运动员到达，统计比赛名次");



    }


    private static void baseTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        new Thread(() -> run(1, countDownLatch)).start();
        new Thread(() -> run(2, countDownLatch)).start();
        new Thread(() -> run(3, countDownLatch)).start();

        countDownLatch.await();

        LOGGER.info("Main is end");
    }

    private static void run(int i, CountDownLatch countDownLatch) {
        LOGGER.info("Worker-" + i + "is running");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info("Worker-" + i + "is end");
        countDownLatch.countDown();
    }
}