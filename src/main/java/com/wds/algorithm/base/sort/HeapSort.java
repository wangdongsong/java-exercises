package com.wds.algorithm.base.sort;

/**
 * 堆排序
 * Created by wangdongsong1229@163.com on 2016/10/16.
 */
public class HeapSort {
    private int[] element;

    public HeapSort(int maxSize) {
        this.element = new int[maxSize + 1];
        element[0] = 0;
    }

    public boolean isEmpty() {
        return element[0] == 0;
    }

    public boolean isFull() {
        return element[0] == element.length - 1;
    }

    /**
     * 堆中插入元素
     * @param value
     */
    public void insert(int value) {
        if (isFull()) {
            throw new IndexOutOfBoundsException("大顶堆已满");
        }

        if (isEmpty()) {
            element[1] = value;
        } else {
            //确认新增元素的下标
            int i = element[0] + 1;
            while (i != 1 && value > element[i / 2]) {
                //如果比爷节点值大，则爷节点的值下移
                element[i] = element[i / 2];
                i /= 2;
            }
            //最终把值插入指定位置
            element[i] = value;
        }
        element[0]++;
    }

    /**
     * 删除大顶堆的根节点元素
     */
    public int delete() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("大顶堆已空");
        }

        //删除元素，先赋值为最后一个有效元素
        int deleteElement = element[1];
        element[1] = element[element[0]];
        element[0]--;
        int temp = element[1];
        //重建堆
        int parent = 1;
        int child = 2;
        //循环至叶子节点
        while (child <= element[0]) {
            if (child < element[0] && element[child] < element[child + 1]) {
                //首先保证child + 1之后不能越界
                //接着如果左孩子的值小于右孩子的值，那么选择使用右孩子进行比较和交换
                child++;
            }
            if (temp > element[child]) {
                break;
            } else {
                element[parent] = element[child];
                parent = child;
                child *= 2;
            }
        }
        //把最后一个有效元素放到该放的位置
        element[parent] = temp;
        return deleteElement;
    }

    public void printAll() {
        for (int i = 1; i < element[0] +1; i++) {
            System.out.print(element[i]);
            if (i != element[0]) {
                System.out.print(",");
            }
        }

        System.out.println();
    }

    public void sort() {
        if (isEmpty()) {
            throw new RuntimeException("请初始化数据");
        }

        int size = element[0];
        for (int i = 0; i < size; i++) {
            int deleteEelement = delete();
            element[element[0] + 1] = deleteEelement;
        }

        for (int i = 1; i < size + 1; i ++) {
            System.out.print(element[i]);
            if (i != size) {
                System.out.print(",");
            }
        }
    }

    public static void main(String[] args) {
        HeapSort sort = new HeapSort(100);
        int[] array = {10, 70, 9, 18, 34, 29, 15, 66, 12, 48};
        for (int i : array) {
            sort.insert(i);
        }
        sort.printAll();

        sort.sort();
        sort.printAll();
    }
}
