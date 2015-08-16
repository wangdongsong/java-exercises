package com.wds.io.best;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO的散射和聚集
 * Created by wangdongsong on 15-8-16.
 */
public class NIOScatterAndGather {

    public static void main(String[] args) {
        createFiles("/home/wangdongsong/Dev/test.txt");
    }


    public static void createFiles(String path) {
        try {
            ByteBuffer bookBuffer = ByteBuffer.wrap("Hello World".getBytes("UTF-8"));
            ByteBuffer autBuffer = ByteBuffer.wrap("test".getBytes("UTF-8"));

            int bookLength = bookBuffer.limit();
            int autLength = autBuffer.limit();
            ByteBuffer[] bufs = new ByteBuffer[]{bookBuffer, autBuffer};
            File file = new File(path);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(file);
            FileChannel fc = fos.getChannel();
            fc.write(bufs);
            fos.close();

            bookBuffer = ByteBuffer.allocate(bookLength);
            autBuffer = ByteBuffer.allocate(autLength);
            bufs = new ByteBuffer[]{bookBuffer, autBuffer};
            file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            fc = fis.getChannel();
            fc.read(bufs);
            String bookName = new String(bufs[0].array(), "UTF-8");
            String autName = new String(bufs[1].array(), "UTF-8");
            System.out.println(bookName + ", " + autName);

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }

}
