package com.wds.algorithm.base.sort;

/**
 * 希尔排序
 * Created by wangdongsong on 2016/10/10.
 */
public class ShellSort {

    private int[] arrays;

    public ShellSort(int[] arrays) {
        this.arrays = arrays;
    }

    public void sort() {
        int temp;
        for (int i = arrays.length / 2; i > 0; i /= 2) {
            for (int k = i; k < arrays.length; k++) {
                for (int j = k; j >=i; j-=i) {
                    if (arrays[j - i] > arrays[j]) {
                        temp = arrays[j - i];
                        arrays[j - i] = arrays[j];
                        arrays[j] = temp;
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
        int[] arr = {5, 9, 19, 5, 3, 7, 6, 1};
        ShellSort sort = new ShellSort(arr);
        sort.sort();
        sort.print();
    }
}
