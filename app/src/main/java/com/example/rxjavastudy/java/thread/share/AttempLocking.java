package com.example.rxjavastudy.java.thread.share;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AttempLocking {
    private ReentrantLock lock = new ReentrantLock();

    public void unTimed() {
        boolean captured = lock.tryLock();
        try {
            System.out.println("tryLock():" + captured);
        } finally {
            if (captured) {
                lock.unlock();
            }
        }
    }

    public void timed() {
        boolean captured = false;
        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println("tryLock(2, TimeUnit.SECONDS) :" + captured);
        } finally {
            if (captured) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final AttempLocking attempLocking = new AttempLocking();
        attempLocking.unTimed();
        attempLocking.timed();
        Thread t = new Thread() {
            @Override
            public void run() {
                attempLocking.lock.lock();
                System.out.println("acquired" + Thread.currentThread());
            }
        };
        t.setDaemon(true);
        t.start();
        Thread.yield();
        Thread.sleep(1000);
        attempLocking.unTimed();
        attempLocking.timed();

    }
}
