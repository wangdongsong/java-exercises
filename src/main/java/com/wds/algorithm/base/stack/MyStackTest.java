package com.wds.algorithm.base.stack;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 利用栈，将10进制转8进制
 * Created by wangdongsong on 2016/10/4.
 */
public class MyStackTest {

    private static final Logger LOGGER = LogManager.getLogger(MyStackTest.class);

    public static void main(String[] args) {
        convert8(100);

    }

    private static void convert8(int num) {
        MyStack stack = new MyStack();
        while (num != 0) {
            stack.push(num % 8);
            num /= 8;
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
    }
}