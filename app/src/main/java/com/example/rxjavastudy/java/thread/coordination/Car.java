package com.example.rxjavastudy.java.thread.coordination;

public class Car {
    private boolean waxOn = false;

    public synchronized void waxed() throws InterruptedException {
        waxOn = true;
        System.out.println("finished wax");
        notifyAll(); //唤醒当前thread 必须是在获得锁的情况下才能调用，否则会出发IllegalMonitorStateException异常 只有等待这个锁得所有线程才会被唤醒
        waitForBuffing();//只有这个方法走完之后才会走这个方法，因为waitForBuffing方法需要锁，必须等synchronized走完才会走这个（死锁？）
    }

    public synchronized void buffed() throws InterruptedException {
        waxOn = false;
        System.out.println("finished buff");
        notifyAll();
        waitForWaxing();
    }

    public synchronized void waitForWaxing() throws InterruptedException {
        while (!waxOn)
            wait();//释放锁，但是会被挂起，不占cpu资源  而sleep不是放锁
    }

    public synchronized void waitForBuffing() throws InterruptedException {
        while (waxOn)//防止wait在notified之后发生。所以选择用这个循环。
            wait();
    }


    public synchronized void hello1() throws InterruptedException {
        System.out.println("hello1");
        Thread.sleep(5000);
    }

    public synchronized void hello2() throws InterruptedException {
        System.out.println("hello2");
        Thread.sleep(5000);
    }

}
