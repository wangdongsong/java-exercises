package com.wds.algorithm.base.sort;

/**
 * 桶排序
 * Created by wangdongsong1229@163.com on  2016/10/7.
 */
public class BucketSort {

    private int buckets[];
    private int[] arrays;

    public BucketSort(int bucketsLength, int[] arrays) {
        this.buckets = new int[bucketsLength];
        this.arrays = arrays;
    }

    /**
     * 排序
     */
    public void sort() {
        if (arrays != null && arrays.length > 1) {
            for (int i = 0; i < arrays.length; i++) {
                //若待排序的元素有重复，其所在下标指向的元素就+1
                buckets[arrays[i]]++;
            }
        }
    }

    public void print() {
        for (int i = buckets.length - 1 ; i >= 0; i--) {
            //元素中的值为几，就说明有多少个相同值的元素，就输出几篇
            for (int j = 0; j < buckets[i]; j++) {
                System.out.println(i);
            }
        }
    }


    public static void main(String[] args) {
        int[] arrays = {3, 2, 5, 7, 9, 8, 6, 5};
        BucketSort bucketSort = new BucketSort(11, arrays);
        bucketSort.sort();
        bucketSort.print();

        System.out.println("-----------");

        for (int i = 0; i < arrays.length; i ++) {
            System.out.println(arrays[i]);
        }
    }

}
