package com.wds.algorithm.base.list;

/**
 * Created by wangdongsong on 2016/10/4.
 */
public class Link {

    private int size = 0;
    private Node first;
    private Node last;

    public Link() {

    }

    /**
     * 尾部插入
     * @param data
     */
    public void addLast(int data) {
        if (size == 0) {
            //为空时，初始化元素
            fillStart(data);
        } else {
            Node node = new Node();
            node.setData(data);
            last.setNext(node);
            //把最后插入的元素设置为链表尾的元素
            last = node;
        }
        size++;
    }

    /**
     * 头部插入
     * @param data
     */
    public void addFirest(int data) {
        if (size == 0) {
            fillStart(data);
        } else {
            Node node = new Node();
            node.setData(data);
            //把元素的下一个益的指针指向头元素
            node.setNext(first);
            //把刚插入的元素设置为头元素
            first = node;
        }
        size++;
    }

    /**
     * 在链表指定位置后面插入新元素
     * @param data
     * @param index
     */
    public void add(int data, int index) {
        if (size > index) {
            if (size == 0) {
                fillStart(data);
                size++;
            } else if (index == 0) {
                addFirest(data);
            } else if (size == index + 1) {
                addLast(data);
            } else {
                Node temp = getNode(index);
                Node node = new Node();
                node.setData(data);
                node.setNext(temp.getNext());

                temp.setNext(node);
                size++;
            }
        } else {
            throw new IndexOutOfBoundsException("链表没有那么长");
        }
    }

    /**
     * 删除头元素
     */
    public void removeFirst() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("链表没有那么长");
        } else if (size == 1) {
            clear();
        } else {
            Node temp = first;
            first = temp.getNext();
            //回收
            temp = null;
            size--;
        }
    }

    /**
     * 删除尾部元素
     */
    public void removeLast() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("链表没有那么长");
        } else if (size == 1) {
            clear();
        } else {
            Node temp = getNode(size - 2);
            temp.setNext((null));
            size--;
        }
    }

    public void removeMiddle(int index) {
        if (size == 0) {
            throw new IndexOutOfBoundsException("链表没有那么长");
        } else if (size == 1) {
            clear();
        } else {
            if (index == 0) {
                removeFirst();
            } else if (size == index + 1) {
                removeLast();
            } else {
                Node temp = getNode(index - 1);
                Node next = temp.getNext();
                temp.setNext(next);
                next = null;
                size--;
            }
        }

    }

    public void printAll() {
        Node temp = first;
        System.out.println(temp.getData());
        for (int i = 0; i < size -1; i ++) {
            temp = temp.getNext();
            System.out.println(temp.getData());
        }
    }

    public int size() {
        return size;
    }

    private void clear() {
        first = null;
        last = null;
        size = 0;
    }

    private Node getNode(int index) {
        Node temp = first;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        return temp;
    }

    private void fillStart(int data) {
        first = new Node();
        first.setData(data);
        last = first;
    }

    /**
     * 反转链表
     */
    public void reverse() {
        Node temp = first;
        last = temp;
        Node next = first.getNext();
        for (int i = 0; i < size - 1; i++) {
            Node nextNext = next.getNext();
            next.setNext(temp);
            temp = next;
            next = nextNext;
        }
        last.setNext(null);
        first = temp;
    }


}
