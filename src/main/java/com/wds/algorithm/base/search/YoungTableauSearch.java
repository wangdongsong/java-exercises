package com.wds.algorithm.base.search;

/**
 * 杨氏矩阵查找
 * Created by wangdongsong1229@163.com on 2016/10/15.
 */
public class YoungTableauSearch {

    private int[][] arrays;

    public YoungTableauSearch(int[][] arrays) {
        this.arrays = arrays;
    }

    /**
     * 递归查找
     * @param x
     * @param y
     * @param target
     * @return
     */
    public boolean recursionSearch(int x, int y, int target) {
        if (x == arrays.length || y == arrays[0].length) {
            return false;
        }

        if (target < arrays[x][y]) {
            return false;
        }

        if (target > arrays[x][y]) {
            System.out.println(String.format("x: %d, y: %d", x, y));
        }

        return recursionSearch(x + 1, y, target) || recursionSearch(x, y + 1, target);
    }

    /**
     * 直接查找
     * @param target
     * @return
     */
    public boolean directSearch(int target) {
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; target >=arrays[i][j] && j < arrays[0].length; j++) {
                if (target == arrays[i][j]) {
                    System.out.println(String.format("x: %d, y: %d", i, j));
                } else if (target < arrays[i][j]) {
                    return false;
                }
            }
        }
        return false;
    }


    /**
     * <pre>
     *     1, 2,  8,  9, 15, 22, 38
     *     2, 5,  9, 12, 16, 23, 42
     *     4, 7, 13, 15, 21, 28, 46
     *     7, 9, 16, 18, 24, 32, 52
     * </pre>
     *
     * <pre>
     * 查找20，优化后的该当如下：
     *     1、从一行起开始查找，找到22，发现比20大。
     *     2、往下查，发现是23>20，第二行向左，16<20，转第三行
     *     3、第三行为21>20，向左为15<20，向下转第四行
     *     4、查找到18<20，向右为24，结果为flase
     *     无法找到20
     * </pre>
     * @param target
     * @return
     */
    public boolean optimizedSearch(int target) {
        int width = arrays[0].length;
        int height = arrays.length;

        if (target >= arrays[0][0]) {
            int i = 0;
            for (; target > arrays[0][i] && i < width-1; i++) {
                if (target == arrays[0][i]) {
                    System.out.println(String.format("x: %d, y: %d", 0, i));
                    return true;
                }
            }
            //当前行没有找到
            if (i > width - 1) {
                i--;
            }

            for (int j = 1; j < height; j++) {
                if (arrays[j][i] == target) {
                    System.out.println(String.format("x: %d, y: %d", i, j));
                    return true;
                } else if (arrays[j][i] > target) {
                    for (; i >= 0; i--) {
                        if (arrays[j][i] == target) {
                            System.out.println(String.format("x: %d, y: %d", i, j));
                            return true;
                        }else if (arrays[j][i] <= target) {
                            break;
                        }
                    }
                    if (i < 0) {
                        i++;
                    }
                } else if (arrays[j][i] < target) {
                    for (; i < width-1; i++) {
                        if (arrays[j][i] == target) {
                            System.out.println(String.format("x: %d, y: %d", i, j));
                            return true;
                        } else if (arrays[j][i] >= target) {
                            break;
                        }
                    }
                    if (i > width - 1) {
                        i--;
                    }
                }
            }
        }
        return false;
    }


}
