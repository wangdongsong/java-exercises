package com.wds.lambdas.stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * 统计来自伦敦的字符串
 *
 * Created by wangdongsong on 15-8-14.
 */
public class ListCount {

    public static final String LONDON = "London";

    public static int forCount(List<String> lists) {
        int count = 0;
        for (String s : lists) {
            if (LONDON.equals(s)) {
                count++;
            }
        }

        return count;
    }

    public static int iteratorCount(List<String> lists) {
        int count = 0;

        Iterator<String> iterator = lists.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if (LONDON.equals(str)) {
                count++;
            }
        }

        return count;
    }

    public static long lambdasCount(List<String> lists) {
        return lists.stream().filter(str -> LONDON.equals(str)).count();
    }

    public static void main(String[] args) {
        List<String> lists = new ArrayList<>();
        lists.add(LONDON);
        lists.add(LONDON);

        lists.add("wds");

        System.out.println(forCount(lists));
        System.out.println(iteratorCount(lists));
        System.out.println(lambdasCount(lists));
    }

}
