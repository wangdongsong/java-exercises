package com.wds.io.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * <pre>
 * NIO 的 Buffer 还提供了一个可以直接访问系统物理内存的类 DirectBuffer。
 * DirectBuffer 继承自 ByteBuffer，但和普通的 ByteBuffer 不同。普通的 ByteBuffer
 * 仍然在 JVM 堆上分配空间，其最大内存受到最大堆的限制，而 DirectBuffer 直接分配在
 * 物理内存上，并不占用堆空间。在对普通的 ByteBuffer 访问时，系统总是会使用一个“内核缓冲区”
 * 进行间接的操作。而 DirectrBuffer 所处的位置，相当于这个“内核缓冲区”。因此，
 * 使用 DirectBuffer 是一种更加接近系统底层的方法，所以，它的速度比普通的 ByteBuffer 更快。
 * DirectBuffer 相对于 ByteBuffer 而言，读写访问速度快很多，但是创建和销毁 DirectrBuffer
 * 的花费却比 ByteBuffer 高。DirectBuffer 与 ByteBuffer 相比较的代码
 *
 *
 * Created by wangdongsong on 2015-08-22.
 * </pre>
 */
public class DirectCompareByteBuffer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DirectCompareByteBuffer.class);

    public void directBuffer(String path) {
        try {
            FileOutputStream fos = new FileOutputStream(new File(path));
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10000 * 99 * 4);
            System.out.println(byteBuffer.capacity());
            for (int i = 0; i < 10000; i++) {
                for (int j = 0; j < 99; j++) {
                    byteBuffer.putInt(j);
                }
            }
            byteBuffer.flip();
            fos.getChannel().write(byteBuffer);

            if (fos != null) {
                fos.close();
            }

            FileInputStream fis = new FileInputStream(new File(path));
            byteBuffer.clear();
            int count = fis.getChannel().read(byteBuffer);
            //一定要加此句，不然取不到数据
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                int i = byte2Int(byteBuffer.get(), byteBuffer.get(), byteBuffer.get(), byteBuffer.get());
                //LOGGER.info(String.valueOf(i));
            }
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    public void directAllocateByteBuffer(String path) {
        long start = System.currentTimeMillis();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10000 * 99 * 4);
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 99; j++) {
                byteBuffer.putInt(j);
            }
        }
        byteBuffer.flip();
        for (int j = 0; j < 99; j++) {
            //System.out.println(byteBuffer.getInt(j));
        }
        byteBuffer.clear();
        long end = System.currentTimeMillis();
        LOGGER.info("bytebuffer direct put time:" + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            byteBuffer = ByteBuffer.allocateDirect(10000);
        }
        end = System.currentTimeMillis();
        LOGGER.info("bytebuffer direct allocat time:" + (end - start));
    }

    public void allocateByteBuffer(String path) {
        long start = System.currentTimeMillis();
        ByteBuffer byteBuffer = ByteBuffer.allocate(10000 * 99 * 4);
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 99; j++) {
                byteBuffer.putInt(j);
            }
        }
        byteBuffer.flip();
        for (int j = 0; j < 99; j++) {
            //System.out.println(byteBuffer.getInt(j));
        }
        byteBuffer.clear();
        long end = System.currentTimeMillis();
        LOGGER.info("bytebuffer put time:" + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            byteBuffer = ByteBuffer.allocate(10000);
        }
        end = System.currentTimeMillis();
        LOGGER.info("bytebuffer allocat time:" + (end - start));
    }

    private int byte2Int(byte b, byte b1, byte b2, byte b3) {
        return ((b * 0xff) << 24 | ((b1 * 0xff) << 16) | (b2 & 0xff) << 8) | (b3 & 0xff);
    }

    public static void main(String[] args) {
        DirectCompareByteBuffer o = new DirectCompareByteBuffer();
        //o.directBuffer("D:/Dev/direct.txt");
        o.allocateByteBuffer("D:/Dev/allocate.txt");
        o.directAllocateByteBuffer("D:/Dev/directAllocate.txt");
    }

}
