package com.example.rxjavastudy.java.thread.coordination;

public class WaxOn1 implements Runnable {

    private Car1 car;

    public WaxOn1(Car1 car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("start wax!");
                Thread.sleep(200);
                car.waxed();  //设置已经wax完成并且唤醒buff线程等待buff结束
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.println("exiting via interrupt");
        }
        System.out.println("ending was on task");
    }
}
