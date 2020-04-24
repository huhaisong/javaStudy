package com.example.rxjavastudy.java.thread.new_class.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class TaskPortion implements Runnable {
    private static int counter = 0;
    private final int id = ++counter;
    private static Random random = new Random(47);
    private final CountDownLatch latch;

    public TaskPortion(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            doWork();
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doWork() throws InterruptedException {
        int time = random.nextInt(2000);
        Thread.sleep(time);
        System.out.println(this + "completed" + time);
    }

    @Override
    public String toString() {
        return "TaskPortion{" +
                "id=" + id +
                '}';
    }
}
