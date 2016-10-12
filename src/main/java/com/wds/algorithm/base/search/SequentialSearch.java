package com.wds.algorithm.base.search;

/**
 * 顺序查找
 * Created by wangdongsong on 2016/10/13.
 */
public class SequentialSearch {

    private int[] array;

    public SequentialSearch(int[] array) {
        this.array = array;
    }

    /**
     * 顺序查找
     * @param key
     * @return
     */
    public int search(int key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 优化的顺序查找<br>
     *
     * 循环部分的比较操作比<code>search</code>方法中少一半
     *
     * @param key
     * @return
     */
    public int searchSentinel(int key) {
        //判断查找的原因是否为头元素
        if (key == array[0]) {
            return 0;
        }

        int temp = array[0];
        array[0] = key;
        int i = array.length - 1;

        while (array[i] != key) {
            i--;
        }

        array[0] = temp;
        if (i == 0) {
            return -1;
        }

        return i;

    }

    public static void main(String[] args) {
        int[] arrays = {1, 3, 5, 7, 9, 6, 4};
        SequentialSearch search = new SequentialSearch(arrays);
        System.out.println(search.search(3));
    }
}
