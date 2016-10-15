package com.wds.io.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 对传统IO、基于Byte的IO，基于内存映射的IO的对比
 * Created by wangdongsong1229@163.com on  2015-08-22.
 */
public class NIOComparator {

    private static final Logger LOGGER = LoggerFactory.getLogger(NIOComparator.class);
    public static final int NUM = 4000000;

    /**
     * 传统IO
     * @param path 文件路径
     */
    public void ioMethod(String path) {
        //写文件
        try {
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File(path))));
            for (int i = 0; i < NUM; i++) {
                dos.writeInt(i);
            }
            if (dos != null) {
                dos.close();
            }
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        //读文件
        try {
            DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(new File(path))));
            for (int i = 0; i < NUM; i++) {
                dis.readInt();
            }
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    /**
     * Byte IO
     * @param path 文件路径
     */
    public void byteMethod(String path) {
        //写文件
        try {
            FileOutputStream fout = new FileOutputStream(new File(path));
            //得到文件通道
            FileChannel fc = fout.getChannel();
            //分配Buffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(NUM * 4);
            for (int i = 0; i < NUM; i++) {
                //整数转为数组
                byteBuffer.put(int2Byte(i));
            }
            //准备写
            byteBuffer.flip();
            fc.write(byteBuffer);

            if (fc != null) {
                fc.close();
            }

            if (fout != null) {
                fout.close();
            }

        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        //读文件
        try {
            FileInputStream fis = new FileInputStream(new File(path));
            FileChannel fc = fis.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(NUM * 4);
            fc.read(byteBuffer);
            if (fc != null) {
                fc.close();
            }
            //准备读数据
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                byte2Int(byteBuffer.get(), byteBuffer.get(), byteBuffer.get(), byteBuffer.get());
            }
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void mapMethod(String path) {
        FileChannel fc = null;
        try {
            fc = new RandomAccessFile(path, "rw").getChannel();
            IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, NUM * 4).asIntBuffer();
            for (int i = 0; i < NUM; i++) {
                ib.put(i);
            }
            if (fc != null) {
                fc.close();
            }
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        try {
            fc = new FileInputStream(path).getChannel();
            MappedByteBuffer mbf = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            mbf.asIntBuffer();
            while (mbf.hasRemaining()) {
                mbf.get();
            }
            if (fc != null) {
                fc.close();
            }
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    private int byte2Int(byte b, byte b1, byte b2, byte b3) {
        return ((b * 0xff) << 24 | ((b1 * 0xff) << 16) | (b2 & 0xff) << 8) | (b3 & 0xff);
    }

    private byte[] int2Byte(int src) {
        byte[] bytes = new byte[4];
        //最低位
        bytes[3] = (byte) (src & 0xff);
        //次低位
        bytes[2] = (byte) ((src >> 8) & 0xff);
        //次高位
        bytes[1] = (byte) ((src >> 16) & 0xff);
        //最高位，无符号右移
        bytes[0] = (byte) ((src >>> 24));
        return bytes;
    }

}
