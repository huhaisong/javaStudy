package com.example.rxjavastudy.java.thread.new_class.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {
    static final int SIZE = 100;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new WaitingTask(countDownLatch));
        }
        for (int i = 0; i < SIZE; i++) {
            executorService.execute(new TaskPortion(countDownLatch));
        }
        System.out.println("Launched all tasks");
        executorService.shutdown();
    }
}
