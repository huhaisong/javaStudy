package com.example.rxjavastudy.java.thread.coordination;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaxOMatic {
    public static void main(String[] args) throws InterruptedException {
        final Car car = new Car();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new WaxOn(car));
        executorService.execute(new WaxOff(car));
        Thread.sleep(5000);
        executorService.shutdownNow();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    car.hello1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Thread.sleep(300);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    car.hello2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
