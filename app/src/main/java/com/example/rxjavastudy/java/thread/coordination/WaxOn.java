package com.example.rxjavastudy.java.thread.coordination;

public class WaxOn implements Runnable {

    private Car car;

    public WaxOn(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("start wax!");
                Thread.sleep(2000);
                car.waxed();  //设置已经wax完成并且唤醒buff线程等待buff结束
            }
        } catch (InterruptedException e) {
            System.out.println("exiting via interrupt");
        }
        System.out.println("ending was on task");
    }
}
