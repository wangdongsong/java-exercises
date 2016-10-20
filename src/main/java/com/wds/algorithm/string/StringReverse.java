package com.wds.algorithm.string;

/**
 * 字符串反转
 * Created by wangdongsong1229@163.com on 2016/10/19.
 */
public class StringReverse {

    public static void main(String[] args) {
        String str = "abcd";
        System.out.println(reverseSwap(str, 0, str.length() -1 ));
    }

    /**
     * 互换位置实现字符串反转
     * @param str
     * @param start
     * @param end
     * @return
     */
    public static String reverseSwap(String str, int start, int end) {
        char[] array = str.toCharArray();
        int mid = (end - start) / 2 + start;
        for (int i = 0; i <= mid - start; i++) {
            swap(array, start + i, end - i);
        }
        return String.valueOf(array);
    }

    /**
     * 顺序反转
     * @param str
     * @param start
     * @param end
     * @return
     */
    public static String reverseOrder(String str, int start, int end) {
        //先转换为字符数组
        char[] array = str.toCharArray();
        //初始化已完成反转第1个字符的位置
        int finish = end + 1;

        while (finish > start) {
            char temp = array[start];
            for (int j = start + 1; j < finish; j++) {
                array[j - 1] = array[j];
            }

            array[finish - 1] = temp;
            finish--;
        }
        return String.valueOf(array);
    }

    /**
     * 数组指定位置互换
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
