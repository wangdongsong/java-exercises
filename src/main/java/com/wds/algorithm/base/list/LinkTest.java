package com.wds.algorithm.base.list;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by wangdongsong on 2016/10/5.
 */
public class LinkTest {

    private final static Logger LOGGER = LogManager.getLogger(LinkTest.class);


    public static void main(String[] args) {
        Link link = new Link();
        link.addLast(1);
        link.addLast(2);
        link.addLast(3);
        link.addLast(4);

        link.printAll();

        link.reverse();

        link.printAll();
    }

}
