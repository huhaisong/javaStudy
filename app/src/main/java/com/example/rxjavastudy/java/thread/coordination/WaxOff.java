package com.example.rxjavastudy.java.thread.coordination;

public class WaxOff implements Runnable {
    Car car;

    public WaxOff(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();
                System.out.println("start stuff!");
                Thread.sleep(2000);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.println("exiting via interrupt");
        }
        System.out.println("ending was off task");
    }
}
