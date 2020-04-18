package com.example.rxjavastudy.java.thread.cancel;

public class SleepBlocked implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            System.out.println("SleepBlocked InterruptedException!");
        }
        System.out.println("exiting sleep block thread!");
    }
}
