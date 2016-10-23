package com.wds.algorithm.string;

/**
 * 字符串旋转， "abcdefg"，根据下标3进行旋转，成为"efgabcd"
 * Created by wangdongsong1229@163.com on 2016/10/21.
 */
public class StringRotation {

    public static String rotate(String str, int index) {
        str = StringReverse.reverseSwap(str, 0, index);
        str = StringReverse.reverseSwap(str, index + 1, str.length() - 1);
        str = StringReverse.reverseSwap(str, 0, str.length() - 1);
        return str;
    }

    public static void main(String[] args) {
        String str = "abcdefg";
        System.out.println(rotate(str, 3));
    }
}
