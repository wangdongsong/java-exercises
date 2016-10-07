package com.wds.algorithm.base.sort;

/**
 * 冒泡排序
 * Created by wangdongsong on 2016/10/7.
 */
public class BubbleSort {

    private int[] arrays;

    public BubbleSort(int[] arrays) {
        this.arrays = arrays;
    }

    /**
     * 从小到大
     */
    public void sortASC() {
        int temp = 0;
        if (arrays != null && arrays.length > 0) {
            for (int i = 1; i < arrays.length; i++) {
                for (int j = 0; j < arrays.length - i; j++) {
                    if (arrays[j] > arrays[j + 1]) {
                        temp = arrays[j];
                        arrays[j] = arrays[j + 1];
                        arrays[j + 1] = temp;
                    }
                }
            }
        }
    }

    public void sortDESC() {
        int temp = 0;
        if (arrays != null && arrays.length > 0) {
            for (int i = 1; i < arrays.length; i++) {
                for (int j = 0; j < arrays.length - i; j++) {
                    if (arrays[j] < arrays[j + 1]) {
                        temp = arrays[j];
                        arrays[j] = arrays[j + 1];
                        arrays[j + 1] = temp;
                    }
                }
            }
        }
    }

    public void print() {
        for (int i = 0; i < arrays.length; i++) {
            System.out.print(arrays[i] + " ,");
        }
    }

    public static void main(String[] args) {
        int[] arrays = {9, 3, 5, 7, 2, 1, 8};
        BubbleSort bubbleSort = new BubbleSort(arrays);

        bubbleSort.sortDESC();
        bubbleSort.print();
    }
}
