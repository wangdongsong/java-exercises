package com.wds.algorithm.base.sort;

/**
 * 简单选择排序
 * Created by wangdongsong on 2016/10/11.
 */
public class SelectAort {

    private int[] arrays;

    public SelectAort(int[] arrays) {
        this.arrays = arrays;
    }

    public void sort() {

        int length = arrays.length;
        for (int i = 0; i < length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arrays.length; j++) {
                if (arrays[j] < arrays[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = arrays[minIndex];
                arrays[minIndex] = arrays[i];
                arrays[i] = temp;
            }

        }

    }

    public void print() {
        for (int i = 0; i < arrays.length; i++) {
            System.out.print(arrays[i] + " ,");
        }
    }

    public static void main(String[] args) {
        int[] arrs = {5, 9, 1, 9, 5, 3, 7, 6, 1};
        SelectAort sort = new SelectAort(arrs);
        sort.sort();
        sort.print();
    }

}
