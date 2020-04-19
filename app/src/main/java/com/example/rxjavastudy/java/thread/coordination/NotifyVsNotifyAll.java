package com.example.rxjavastudy.java.thread.coordination;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotifyVsNotifyAll {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Task());
        }
        executorService.execute(new Task2());
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            private boolean prod = true;

            @Override
            public void run() {
                if (prod) {
                    System.out.println("notify");
                    Task.blocker.prod();
                    prod = false;
                } else {
                    System.out.println("notifyall");
                    Task.blocker.prodAll();
                    prod = true;
                }
            }
        }, 400, 400);
        Thread.sleep(5000);
        timer.cancel();
        System.out.println("timer canceled");
        Thread.sleep(500);
        System.out.println("Task2.blocker.prodAll()");
        Task2.blocker.prodAll();
        Thread.sleep(500);
        System.out.println("shut down");
        executorService.shutdownNow();
    }
}
