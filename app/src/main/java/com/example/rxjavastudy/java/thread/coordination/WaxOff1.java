package com.example.rxjavastudy.java.thread.coordination;

public class WaxOff1 implements Runnable {
    Car1 car;

    public WaxOff1(Car1 car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();
                System.out.println("start stuff!");
                Thread.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.println("exiting via interrupt");
        }
        System.out.println("ending was off task");
    }
}
