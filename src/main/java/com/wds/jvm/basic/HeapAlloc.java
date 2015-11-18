package com.wds.jvm.basic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 堆分配测试
 * Created by wds on 2015/11/15.
 */
public class HeapAlloc {
    private static final Logger LOGGER = LogManager.getLogger(HeapAlloc.class);

    public static void main(String[] args) {
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        long freeMemory = Runtime.getRuntime().freeMemory() / 1024 / 1024;
        long totalMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;

        LOGGER.info("maxMemory=" + maxMemory + "; freeMemory=" + freeMemory + "; totalMemory=" + totalMemory);

        //分配1M的内存空间
        byte[] bytes = new byte[5 * 1024 * 1024];
        LOGGER.info("分配1M内存空间");
        maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        freeMemory = Runtime.getRuntime().freeMemory() / 1024 / 1024;
        totalMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        LOGGER.info("maxMemory=" + maxMemory + "; freeMemory=" + freeMemory + "; totalMemory=" + totalMemory);

        //分配1M的内存空间
        bytes = new byte[4 * 1024 * 1024];
        LOGGER.info("分配4M内存空间");

        maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        freeMemory = Runtime.getRuntime().freeMemory() / 1024 / 1024;
        totalMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        LOGGER.info("maxMemory=" + maxMemory + "; freeMemory=" + freeMemory + "; totalMemory=" + totalMemory);
    }
}
