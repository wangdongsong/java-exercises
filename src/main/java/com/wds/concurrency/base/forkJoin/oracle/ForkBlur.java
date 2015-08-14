package com.wds.concurrency.base.forkJoin.oracle;

import java.util.concurrent.RecursiveAction;

/**
 * Created by wangdongsong on 15-8-9.
 */
public class ForkBlur extends RecursiveAction {

    private int[] mSource;
    private int mStart;
    private int mLength;
    private int[] mDestination;

    private int mBlurWidth = 15;

    protected static int sThreshold = 10000;

    public ForkBlur(int[] src, int start, int length, int[] dst) {
        this.mSource = src;
        this.mStart = start;
        this.mLength = length;
        this.mDestination = dst;
    }

    protected void computeDirectly() {
        int sidePixels = (mBlurWidth - 1) / 2;
        for (int index = mStart; index < mStart + mLength; index++) {

            //计算平均值
            float rt = 0, gt = 0, bt = 0;
            for (int mi = -sidePixels; mi < sidePixels; mi++) {

                int mIndex = Math.min(Math.max(mi + index, 0), mSource.length - 1);

                int pixel = mSource[mIndex];

                rt += (float) ((pixel & 0x00ff0000) >> 16) / mBlurWidth;
                gt += (float) ((pixel & 0x00ff0000) >> 8) / mBlurWidth;
                bt += (float) ((pixel & 0x00ff0000) >> 0) / mBlurWidth;

            }

            int dpixel = (0xff000000) | (((int) rt) << 16) | (((int) gt) << 8) | (((int) bt) << 0);
            mDestination[index] = dpixel;
        }
    }

    @Override
    protected void compute() {
        if (mLength < sThreshold) {
            computeDirectly();
            return;
        }

        int split = mLength / 2;
        invokeAll(new ForkBlur(mSource, mStart, split, mDestination), new ForkBlur(mSource, mStart + split, mLength - split, mDestination));

    }
}
