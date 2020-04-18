package com.example.rxjavastudy.java.thread.cancel;

public class SynchronizedBlocked implements Runnable {
    public synchronized void f() {
        while (true)
            Thread.yield();
    }

    public SynchronizedBlocked() {
        new Thread() {
            @Override
            public void run() {
                f();
            }
        }.start();
    }


    @Override
    public void run() {
        System.out.println("trying to call f()");
        f();
        System.out.println("exiting synchronized block");
    }
}
