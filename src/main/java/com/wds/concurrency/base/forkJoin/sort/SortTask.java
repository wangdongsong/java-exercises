package com.wds.concurrency.base.forkJoin.sort;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * Created by wangdongsong on 15-8-9.
 */
public class SortTask extends RecursiveAction {

    private long[] array;

    private int lo;

    private int hi;

    private int THRESHOLD = 0;

    public SortTask(long[] array) {
        this.array = array;
        this.lo = 0;
        this.hi = array.length - 1;
    }

    public SortTask(long[] array, int lo, int hi) {
        this.array = array;
        this.hi = hi;
        this.lo = lo;
    }

    @Override
    protected void compute() {
        if (hi - lo < THRESHOLD) {
            sequentiallySort(array, lo, hi);
        } else {
            int pivot = partition(array, lo, hi);
            System.out.println("\npivot =" + pivot + ", low = " + lo + ", high = " + hi);
            System.out.println("array" + Arrays.toString(array));
            invokeAll(new SortTask(array, lo, pivot - 1), new SortTask(array, pivot + 1, hi));
        }
    }

    private int partition(long array[], int lo, int hi) {
        long x = array[hi];

        int i = lo - 1;

        for (int j = lo; j < hi; j++) {
            System.out.println("InitSrc Array:" + Arrays.toString(array));
            if (array[j] <= x) {
                i++;
                swap(array, i, j);
                System.out.println("Initial Array:" + Arrays.toString(array));
            }
        }
        swap(array, i + 1, hi);
        return i + 1;
    }

    private void swap(long[] array, int i, int j) {

        if (i != j) {
            long temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

    }


    private void sequentiallySort(long[] array, int lo, int hi) {
        Arrays.sort(array, lo, hi + 1);
    }
}
