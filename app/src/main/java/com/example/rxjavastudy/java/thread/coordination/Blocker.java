package com.example.rxjavastudy.java.thread.coordination;

public class Blocker {
    synchronized void waitingCall() {
        try {
            while (!Thread.interrupted()) {
                wait();
                System.out.println(Thread.currentThread() + "");
            }
        } catch (InterruptedException e) {
//            e.printStackTrace();
        }
    }

    synchronized void prod(){notify();}
    synchronized void prodAll(){notifyAll();}
}
