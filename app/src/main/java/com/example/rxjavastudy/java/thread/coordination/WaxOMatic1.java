package com.example.rxjavastudy.java.thread.coordination;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaxOMatic1 {
    public static void main(String[] args) throws InterruptedException {
        final Car1 car = new Car1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new WaxOn1(car));
        executorService.execute(new WaxOff1(car));
        Thread.sleep(5000);
        executorService.shutdownNow();
    }
}
