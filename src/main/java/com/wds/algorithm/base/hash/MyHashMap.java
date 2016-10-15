package com.wds.algorithm.base.hash;

import javax.swing.text.html.parser.Entity;

/**
 * 自定义散列
 * Created by wangdongsong1229@163.com on  2016/9/25.
 */
public class MyHashMap {

    /**
     * 默认散列的初始化长度
     * 设置小一点，我们能够看清楚扩容
     * 在实际使用中其实可以在初始化时传参，扩容是比较耗时
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 4;

    /**
     * 扩容因子
     */
    private static final float LOAD_FACTOR = 0.77f;

    /**
     * 散列数组
     */
    private MyEntry[] table = new MyEntry[DEFAULT_INITIAL_CAPACITY];

    /**
     * 散列表元素的个数
     */
    private int size = 0;

    /**
     * 散列表使用地址的个数
     */
    private int use = 0;

    /**
     * 添加/修改
     * @param key
     * @param value
     */
    public void put(int key, int value){
        int index = hash(key);

        if (table[index] == null) {
            table[index] = new MyEntry(-1, -1, null);
        }

        MyEntry e = table[index];

        if (e.next == null) {
            //不存在值，向链表添加，有可能扩容，要使用table属性
            table[index].next = new MyEntry(key, value, null);
            size++;
            use++;
            //不存在值，说明是个未使用过的地址，需要判断是否需要扩容
            if (use > table.length * LOAD_FACTOR) {
                resize();
            }
        } else {
            //本身存在值，修改已有值
            for (e = e.next; e!=null; e = e.next) {
                int k = e.key;
                if (k == key) {
                    e.value = value;
                    return;
                }
            }

            //不存在相同的值，直接向链表中添加元素
            MyEntry temp = table[index].next;
            MyEntry newEntry = new MyEntry(key, value, temp);
            table[index].next = newEntry;
            size++;
        }
    }

    public void remove(int key) {

        int index = hash(key);
        MyEntry e = table[index];
        MyEntry pre = table[index];

        if (e != null && e.next != null) {
            for (e = e.next; e!= null; pre =  e, e = e.next) {
                int k = e.key;
                if (k == key) {
                    pre.next = e.next;
                    size--;
                    return;
                }
            }

        }

    }

    public int get(int key) {
        int index = hash(key);
        MyEntry e = table[index];
        if (e != null && e.next != null) {
            for (e = e.next; e!= null; e = e.next) {
                int k = e.key;
                if (k == key) {
                    return e.value;
                }
            }
        }
        return -1;
    }

    public int size() {
        return size;
    }

    public int length() {
        return table.length;
    }

    private void resize() {
        int newLength = table.length * 2;
        MyEntry[] oldTable = table;
        table = new MyEntry[newLength];
        use = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null && oldTable[i].next != null) {
                if (oldTable[i] != null && oldTable[i].next != null) {
                    MyEntry e = oldTable[i];
                    while (null != e.next) {
                        MyEntry next = e.next;

                        //重新计算哈希值，放入新的地址中
                        int index = hash(next.key);

                        if (table[index] == null) {

                            use++;
                            table[index] = new MyEntry(-1, -1, null);

                        }

                        MyEntry temp = table[index].next;

                        MyEntry newEntry = new MyEntry(next.key, next.value, temp);
                        table[index].next = newEntry;

                        e = next;

                    }
                }
            }
        }

    }

    private int hash(int key) {
        return key % table.length;
    }

}
