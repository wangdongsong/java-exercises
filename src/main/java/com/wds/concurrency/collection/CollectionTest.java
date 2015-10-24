package com.wds.concurrency.collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Map、List、Set
 * Created by wds on 2015/10/24.
 */
public class CollectionTest {

    private static final Logger LOGGER = LogManager.getLogger(CollectionTest.class);

    public static void main(String[] args) {
        mapTest();
        listTest();
        setTest();
    }

    /**
     * <pre>
     * ConcurrentHashMap允许多个修改操作进行，其关键在于使用了锁分离技术，即代码块锁，而不是方法锁。
     * 它使用了多个锁来控制对hash表的不同部分进行的修改。其内部使用段（Segment）来表示这些不同的部分，
     * 每个段其实就是一个小的hash table，它们有自己的锁（由ReentrantLock实现）。只要多个修改操作不允许在
     * 不同的段上，它们就可以并发进行
     * </pre>
     */
    public static void mapTest() {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 10; i++) {
            map.put(String.valueOf(i), i);
        }

        LOGGER.info(map);

    }

    /**
     * <pre>
     * CopyOnWrite的机制：即时复制容器。当我们往一个容器添加元素的时候，不直接往当前容器添加，而是先将当前容器进行Copy，
     * 复制出一个新的容器，然后新的容器里添加元素，添加完元素之后，再将原容器的引用指向新的容器。这样做的好处是我们可以对
     * CopyOnWrite容器进行并发地读，而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，
     * 读和写不同的容器。
     *
     * CopyOnWrite的应用场景：CopyOnWrite并发容器用于读多写少的并发场景，比如黑白名单，商品类访问。
     *
     * 使用CopyOnWrite容器需要注意的两件事情：
     * 1、减少扩容开销，根据实际需要，初始化容器的大小，避免写时CopyOnWrite扩容的开销。
     * 2、使用批量添加。因为每次添加，容器每次都会进行复制，所以减少添加次数，可以减少容器复制的次数。
     *
     * CopyOnWrite的缺点：
     * 1、内存占用问题：写复制机制，内存会同时存在两个对象的内存，有可能导致频繁的YongGC和FullGC。
     * 2、数据一致性问题：CopyOnWrite只能保证数据的最终一致，不能保证实时一致性。
     * </pre>
     */
    public static void listTest() {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i + i));
        }

        for (String string : list){
            LOGGER.info(string);
        }
    }

    public static void setTest() {
        CopyOnWriteArraySet<String> list = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i + i));
        }

        for (String string : list){
            LOGGER.info(string);
        }
    }
    
    
}
