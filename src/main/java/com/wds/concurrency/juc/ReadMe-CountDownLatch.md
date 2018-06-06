#CountDownLatch

同步功能辅助类，使用效果是给定一个计数，当使用CountDownLatch类的线程判断计数不为0时，则呈wait状态，如果为0时则继续进行。

实现等待与继续运行的效果分别需要使用await()和countDown()方法来进行，调用await()方法时判断计数是否为0，如果不为0则呈等待状态。
其它线程可以调用countDown()方法将计数减1，当计数减到0时，呈等待线程继续运行，则getCount()就是获得当前计数个数。

**计数无法重置，如果需要重置计数，可考虑使用CyclicBarrier类**