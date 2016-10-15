package com.wds.algorithm.base.stack;

import java.util.Arrays;

/**
 * 栈的特点：先进后出或后进先出的原则，只能在一端操作
 * Created by wangdongsong1229@163.com on  2016/9/26.
 */
public class MyStack {

    private int size = 0;

    private int[] array;

    public MyStack() {
        this(10);
    }

    public MyStack(int size) {
        if(size <= 0){
            size = 10;
        }
        array = new int[size];
    }

    /**
     * 入栈
     *
     * 容易不够时扩容
     * @param item
     */
    public void push(int item) {
        if (size == array.length) {
            array = Arrays.copyOf(array, size * 2);
        }

        array[size++] = item;
    }

    /**
     * 获取栈顶元素，但没出栈
     * @return
     */
    public int peek() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("stack is null");
        }

        return array[size - 1];
    }

    /**
     * 出栈，同时获取栈顶元素
     * @return
     */
    public int pop() {
        int item = peek();
        //直接使元素个数减1，不需要真的清除元素，下次入栈直接覆盖旧元素的值
        size--;
        return item;
    }

    public boolean isFull() {
        return size == array.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

}
