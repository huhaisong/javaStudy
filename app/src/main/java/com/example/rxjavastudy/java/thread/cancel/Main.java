package com.example.rxjavastudy.java.thread.cancel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Entrance(i));
        }
        Thread.sleep(3000);
        Entrance.cancel();
        executorService.shutdown();
        if (!executorService.awaitTermination(2500, TimeUnit.MILLISECONDS))
            System.out.println("some tasks were not terminated");
        System.out.println("total:" + Entrance.getCountValue());
        System.out.println("sum of entrances: " + Entrance.sumEntrances());
    }
}
