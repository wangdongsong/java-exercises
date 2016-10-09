package com.wds.algorithm.base.sort;

/**
 * Created by wangdongsong on 2016/10/8.
 */
public class InsertSort {
    private int[] arrays;

    public InsertSort(int[] arrays) {
        this.arrays = arrays;
    }

    public void sort() {
        if (arrays == null) {
            throw new NullPointerException("arrays is null");
        }

        int length = arrays.length;
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                int temp = arrays[i];
                int j = i;
                for (; j > 0 & arrays[j - 1] > temp; j--) {
                    arrays[j] = arrays[j - 1];
                }

                arrays[j] = temp;
            }
        }
    }

    public void print() {
        for (int i = 0; i < arrays.length; i++) {
            System.out.print(arrays[i] + " ,");
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 9, 19, 5, 3, 7, 6, 1};
        InsertSort sort = new InsertSort(arr);
        sort.sort();
        sort.print();
    }


}
