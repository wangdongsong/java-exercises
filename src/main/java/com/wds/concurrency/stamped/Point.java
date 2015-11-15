package com.wds.concurrency.stamped;

import java.util.concurrent.locks.StampedLock;

/**
 * Java Doc Sample
 * Created by wds on 2015/10/18.
 */
public class Point {
    private double x, y;
    private final StampedLock stampedLock = new StampedLock();

    public void move(double deltaX, double deltaY) {
        long stamp = stampedLock.writeLock();

        try {
            x += deltaX;
            y += deltaY;
        } finally {
            stampedLock.unlock(stamp);
        }

    }

    public double distanceFromOrigin() {
        long stamp = stampedLock.tryOptimisticRead();
        double currentX = x;
        double currentY = y;

        if (!stampedLock.validate(stamp)) {
            stamp = stampedLock.readLock();
            try {
                currentX = x;
                currentY = y;
            }finally {
                stampedLock.unlockRead(stamp);
            }

        }
        return  Math.sqrt(currentX * currentX + currentY * currentY);
    }

    public void moveIfAtOrigin(double newX, double newY) {
        long stamp = stampedLock.readLock();
        try {
            while (x == 0.0 && y == 0.0) {
                long ws = stampedLock.tryConvertToReadLock(stamp);

                if (ws != 0L) {
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                } else {
                    stampedLock.unlockRead(stamp);
                    stamp = stampedLock.writeLock();
                }
            }
        } finally {
            stampedLock.unlock(stamp);
        }
    }
}
