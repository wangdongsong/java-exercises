package com.wds.concurrency.juc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于Semaphore的生产者&消费者，并且限制生产者&消费者的数量
 * Created by wangdongsong1229@163.com on 2017/1/23.
 */
public class SemaphoreProducerConsumer {

    private static final Logger LOGGER = LogManager.getLogger(SemaphoreProducerConsumer.class);

    //同时有10个生产者
    private volatile Semaphore producerSemaphore = new Semaphore(10);
    //同时有20个消费者
    private volatile Semaphore consumerSemaphore = new Semaphore(20);
    private volatile Lock lock = new ReentrantLock();
    private volatile Condition producerCondition = lock.newCondition();
    private volatile Condition consumerCondition = lock.newCondition();

    private volatile Object[] producePosition = new Object[4];

    public static void main(String[] args) {

        SemaphoreProducerConsumer spc = new SemaphoreProducerConsumer();

        for (int i = 0; i < 60; i++) {
            new Thread(() -> {
                spc.set();
            }).start();

            new Thread( () -> {
                spc.get();
            }).start();
        }
    }

    private boolean isEmpty() {
        boolean isEmpty = true;

        for (int i = 0; i < producePosition.length; i++) {
            if (producePosition[i] != null) {
                isEmpty = false;
                break;
            }
        }

        return isEmpty ? true : false;
    }

    private boolean isFull() {
        boolean isFull = true;
        for (int i = 0; i < producePosition.length; i++) {
            if (producePosition[i] == null) {
                isFull = false;
                break;
            }
        }

        return isFull;
    }

    public void set() {
        try {
            producerSemaphore.acquire();
            lock.lock();
            while (isFull()) {
                //生产者等待
                producerCondition.await();
            }

        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }

        for (int i = 0 ; i < producePosition.length; i++) {
            if (producePosition[i] == null) {
                producePosition[i] = "data";
                LOGGER.info(Thread.currentThread().getName() + " produce " + producePosition[i]);
                break;
            }
        }

        consumerCondition.signalAll();
        lock.unlock();
        producerSemaphore.release();
    }

    public void get() {
        try {
            consumerSemaphore.acquire();
            lock.lock();
            while (isEmpty()) {
                consumerCondition.await();
            }
            for (int i = 0; i < producePosition.length; i++) {
                if (producePosition[i] != null) {
                    LOGGER.info(Thread.currentThread().getName() + " consume " + producePosition[i]);
                    producePosition[i] = null;
                    break;
                }
            }
            producerCondition.signalAll();
            lock.unlock();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }finally {
            consumerSemaphore.release();
        }
    }


}
