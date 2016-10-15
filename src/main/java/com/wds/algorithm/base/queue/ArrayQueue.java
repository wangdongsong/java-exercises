package com.wds.algorithm.base.queue;

/**
 * 顺序队
 * Created by wangdongsong1229@163.com on  2016/10/4.
 */
public class ArrayQueue {

    private final Object[] items;

    private int head = 0;
    private int tail = 0;

    public ArrayQueue(int capacity) {
        this.items = new Object[capacity];
    }

    /**
     * 入队
     * @param item
     * @return
     */
    public boolean put(Object item) {
        if (head == (tail + 1) % items.length) {
            //队列已满
            return false;
        }
        //存储
        items[tail] = item;

        //tail的位置后移
        tail = (tail + 1) % items.length;

        return  true;
    }

    /**
     * 获取队列头元素，不出队
     * @return
     */
    public Object peek() {
        if (head == tail) {
            return null;
        }
        return items[head];
    }

    /**
     * 出队
     * @return
     */
    public Object poll() {
        if (head == tail) {
            return null;
        }

        Object item = items[head];
        items[head] = null;
        //head位置后移
        head = (head + 1) % items.length;
        return head;
    }

    public boolean isFull() {
        return head == (tail + 1) % items.length;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public int size() {
        if (tail >= head) {
            return tail - head;
        } else {
            return tail + items.length - head;
        }
    }

}
