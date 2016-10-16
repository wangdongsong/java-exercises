package com.wds.algorithm.base.sort;

/**
 * 二分插入排序
 * Created by wangdongsong1229@163.com on 2016/10/15.
 */
public class BinarySort {
    private int[] arrays;

    public BinarySort(int[] arrays) {
        this.arrays = arrays;
    }

    public void sort() {
        if (arrays == null) {
            throw new IndexOutOfBoundsException("arrays is null");
        }
        if (arrays.length > 0) {
            for (int i=1;i <arrays.length; i++) {
                int temp = arrays[i];
                //二分查找插入的位置
                int insertIndex = binarySerch(i - 1, temp);
                if (i != insertIndex) {
                    //移动需要移动的元素
                    for (int j = i -1; j >= insertIndex; j--) {
                        arrays[j] = arrays[j - 1];
                    }
                    //插入元素
                    arrays[insertIndex] = temp;
                }
            }
        }
    }

    public void print() {
        for (int i = 0; i < arrays.length; i++) {
            System.out.print(arrays[i] + " ,");
        }
    }

    private int binarySerch(int maxIndex, int data) {
        int start = 0;
        int end = maxIndex;
        int mid = -1;
        while (start <= end) {
            mid = (start + end) / 2;
            if (arrays[mid] > data) {
                end = mid - 1;
            } else {
                //如果相等就插入后面
                start = mid + 1;
            }
        }
        return start;
    }

}
