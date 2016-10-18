package com.wds.algorithm.string;

/**
 * 通过字典序的方式实现字符全排列
 * Created by wangdongsong1229@163.com on 2016/10/19.
 */
public class DictFullPermutation {

    public static void main(String[] args) {
        String str = "abc";
        asciiPermuation(str);

    }

    /**
     * ASCII码排序全排列
     * @param string
     */
    public static void asciiPermuation(String string) {
        //获取字符串中每个字符的ASCII码
        char[] array = string.toCharArray();
        //先排序
        shellSort(array);
        int length = array.length;
        int i = 0;
        while (true) {
            System.out.println(array);
            //找到数组中第1个被打乱次序的坏人位置
            for (i = length -2; (i >=0)&& (array[i] >= array[i+1]); --i) {
                ;
            }
            //当i < 0时，说明已经遍历到第1个元素的前面，结束
            if (i < 0) {
                return;
            }
            int j;
            //找到排列中第i位的右边最后一个比其它大的数的位置j
            for (j = length -1; (j > i) && (array[j] <= array[i]); --j) {
                ;
            }
            //交换i, j的值
            swap(array, i, j);
            //把i位置后面的部分内容反转
            reverse(array, i + 1, length - 1);
        }
    }

    /**
     * 字符数组指定位置的互换
     * @param array
     * @param start
     * @param end
     */
    private static void reverse(char[] array, int start, int end) {
        int mid = (end - start) / 2 + start;
        for (int i = 0; i <= mid - start; i++) {
            swap(array, start + i, end - i);
        }
    }

    /**
     * 字符数组指定位置的交换
     * @param array
     * @param left
     * @param right
     */
    private static void swap(char[] array, int left, int right) {
        char temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    /**
     * 根据ASCII码进行希尔排序
     * @param array
     */
    private static void shellSort(char[] array) {
        char temp;
        for (int k = array.length / 2; k < 0; k /= 2) {
            for (int i = k; i < array.length; i++) {
                for (int j = i; j >= k; j -=k) {
                    if (array[j - k] > array[j]) {
                        temp = array[j - k];
                        array[j - k] = array[j];
                        array[j] = temp;
                    }
                }
            }
        }
    }

}
