package com.wds.jvm.basic;

/**
 * 局部变更对GC的影响
 * Created by wds on 2015/11/11.
 */
public class StackLocalGC {

    public static void main(String[] args) {
        StackLocalGC gc = new StackLocalGC();
        gc.localVarGC2();
        System.out.print(Boolean.TRUE);
    }

    public void localVarGC1() {
        //6M大小
        byte[] a = new byte[6 * 1024 * 1024 * 256];
        System.gc();
    }

    public void localVarGC2() {
        byte[] a = new byte[6 * 1024 * 1024 * 256];
        a = null;
        System.gc();
    }

    public void localVarGC3() {
        {
            byte[] a = new byte[6 * 1024 * 1024];
        }
        System.gc();
    }

    public void localVarGC4() {
        {
            byte[] a = new byte[6 * 1024 * 1024];
        }
        int c = 10;
        System.gc();
    }

    public void localVarGC5() {
        localVarGC1();
        System.gc();
    }


}
