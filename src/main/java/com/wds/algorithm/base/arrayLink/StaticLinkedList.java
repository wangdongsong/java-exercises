package com.wds.algorithm.base.arrayLink;


/**
 * Created by wangdongsong on 2016/10/5.
 */
public class StaticLinkedList {

    private Element[] elements;

    private int head;

    private int tail;

    private int unUsed;

    private int size;

    /**
     * 初始化
     * @param capacity 容量大小
     */
    public StaticLinkedList(int capacity) {
        elements = new Element[capacity];
        unUsed = 0;
        for (int i = 0; i <capacity - 1; i++) {
            elements[i] = new Element();
            elements[i].setCur(i + 1);
        }

        elements[capacity - 1] = new Element();
        elements[capacity - 1].setCur(-1);

    }

    public void insert(int data, int index) {
        if (index == 0) {
            insertFirst(data);
        } else if (index == size) {
            insertLast(data);
        } else {
            checkFull();

            //获取要插入元素的前一个元素
            Element preElement = get(index);
            //获取一个未被使用的元素作为要插入的元素
            Element unUsedElement = elements[unUsed];
            //记录要插入元素的下标
            int temp = unUsed;
            //将从备用链表中拿出来的元素的下一个元素的数组下标设置为备用链表头
            unUsed = unUsedElement.getCur();
            //将要插入元素的指针设为原本前一个元素的指针指向的下标值（链表操作操作）
            unUsedElement.setCur(preElement.getCur());
            //赋值
            unUsedElement.setData(temp);
            //链表长度+1
            size++;
        }
    }

    public void insertFirst(int data) {
        checkFull();
        Element unUsedElement = elements[unUsed];
        int temp = unUsed;
        unUsed = unUsedElement.getCur();
        unUsed = unUsedElement.getCur();
        unUsedElement.setData(data);
        head = temp;
        size++;
    }

    public void insertLast(int data) {
        checkFull();
        Element unUsedElement = elements[unUsed];
        int temp = unUsed;
        unUsed = unUsedElement.getCur();
        elements[tail].setCur(temp);
        unUsedElement.setData(data);
        tail = temp;
        size++;
    }

    public void deleteFirst() {
        checkEmpty();
        Element deleteElement = elements[head];
        int temp = head;
        head = deleteElement.getCur();
        deleteElement.setCur(unUsed);
        unUsed = temp;
        size--;
    }

    public void deleteLast() {
        delete(size - 1);
    }

    private void delete(int index) {
        if (index == 0) {
            deleteFirst();
        } else {
            checkEmpty();
            Element pre = get(index - 1);
            int del = pre.getCur();
            Element deleteElement = elements[del];
            pre.setCur(deleteElement.getCur());
            if (index == size - 1) {
                tail = index - 1;
            }
            deleteElement.setCur(unUsed);
            unUsed = del;
            size--;
        }
    }

    private void checkEmpty() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("链表为空");
        }
    }

    private Element get(int index) {
        checkEmpty();
        Element element = elements[head];
        for (int i = 0; i < index; i++) {
            element = elements[element.getCur()];
        }
        return element;
    }

    public int size() {
        return size;
    }

    public void printAll() {
        Element element = elements[head];
        System.out.print(element.getData());
        for (int i = 0; i < size; i++) {
            element = elements[element.getCur()];
            System.out.println(element.getData());
        }
    }

    private void checkFull() {
        if (size == elements.length) {
            throw new IndexOutOfBoundsException("数组不够长");
        }
    }



}
