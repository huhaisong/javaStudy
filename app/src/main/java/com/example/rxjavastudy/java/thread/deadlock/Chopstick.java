package com.example.rxjavastudy.java.thread.deadlock;

public class Chopstick {
    private boolean token = false;

    public synchronized void take() throws InterruptedException {
        while (token) {
            wait();
        }
        token = true;
    }

    public synchronized void drop() {
        token = false;
        notifyAll();
    }
}
