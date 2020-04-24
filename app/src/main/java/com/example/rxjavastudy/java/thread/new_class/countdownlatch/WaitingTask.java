package com.example.rxjavastudy.java.thread.new_class.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class WaitingTask implements Runnable {
    private static int count = 0;
    private final int id = count++;
    private final CountDownLatch countDownLatch;

    public WaitingTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            System.out.println("Latch barrier passed for " + this);
        } catch (InterruptedException e) {
            System.out.println("this is interrupted");
        }
    }

    @Override
    public String toString() {
        return "WaitingTask{" +
                "id=" + id +
                '}';
    }
}
