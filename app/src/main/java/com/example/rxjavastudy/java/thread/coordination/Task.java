package com.example.rxjavastudy.java.thread.coordination;

public class Task implements Runnable {
    static Blocker blocker = new Blocker();

    @Override
    public void run() {
        blocker.waitingCall();
    }
}
