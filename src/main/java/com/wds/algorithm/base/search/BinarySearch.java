package com.wds.algorithm.base.search;

/**
 * 二分查找
 * Created by wangdongsong1229@163.com on 2016/10/15.
 */
public class BinarySearch {

    private int[] arrays;

    public BinarySearch(int[] arrays) {
        this.arrays = arrays;
    }

    public int searchRecursion(int target) {
        if (arrays != null) {
            return searchRecursion(target, 0, arrays.length - 1);
        }
        return -1;
    }

    /**
     * 二分查找的递归实现
     * @param target
     * @param start
     * @param end
     * @return
     */
    private int searchRecursion(int target, int start, int end) {

        if (start > end) {
            return -1;
        }

        int mid = start + (end - start) / 2;
        if (arrays[mid] == target) {
            return mid;
        } else if (target < arrays[mid]) {
            return searchRecursion(target, start, mid - 1);
        } else {
            return searchRecursion(target, mid + 1, end);
        }

    }

    /**
     * 二分查找的非递归实现
     * @param target
     * @return
     */
    public int binarySearch(int target) {
        if (arrays == null) {
            return -1;
        }
        int start = 0;
        int end = arrays.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arrays[mid] == target) {
                return mid;
            } else if (target < arrays[mid]) {
                end = mid - 1;
            }else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 5, 7, 9, 11, 13, 19};
        BinarySearch search = new BinarySearch(array);
        System.out.println(search.searchRecursion(3));
    }

}
