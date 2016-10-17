package com.wds.algorithm.string;

/**
 * 字符串全排列-
 * Created by wangdongsong1229@163.com on 2016/10/18.
 */
public class FullPermutation {

    public static void main(String[] args) {
        String str = "abc";
        recursivePermutation(str);
    }

    /**
     * 字符全排序的-递归实现
     * @param str
     */
    public static void recursivePermutation(String str) {
        char[] array = str.toCharArray();
        recursivePermutation(array, 0, array.length - 1);
    }

    /**
     * 字符全排序的-递归实现
     * @param array 字符数组
     * @param start 起始下标
     * @param end 结束下标
     */
    private static void recursivePermutation(char[] array, int start, int end) {
        //当递归到最后一位的时候，start和end相等，此时输出排列
        if (start == end) {
            for (int i = 0; i <= end; i++) {
                System.out.print(array[i]);
            }
            System.out.println();
        }else {
            //一直编历并递归执行后面部分的全排列
            for (int j = start; j <= end; j++) {
                //与当前递归的部分的第1位进行交换
                swap(array, j, start);
                //递归全排列后面的部分
                recursivePermutation(array, start + 1, end);
                //交换回去保持原样
                swap(array, j , start);
            }
        }
    }

    /**
     * 字符数组指定交换的位置
     *
     * @param array
     * @param left
     * @param right
     */
    private static void swap(char[] array, int left, int right) {
        char temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

}
