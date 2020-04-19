package com.example.rxjavastudy.java.thread.coordination.blockqueue;

import com.example.rxjavastudy.java.thread.baseic.LiftOff;

import java.util.concurrent.BlockingQueue;

public class LiftOffRunner implements Runnable {
    private BlockingQueue<LiftOff> rockets;

    public LiftOffRunner(BlockingQueue<LiftOff> rockets) {
        this.rockets = rockets;
    }

    public void add(LiftOff liftOff) {
        try {
            rockets.put(liftOff);
        } catch (InterruptedException e) {
            System.out.println("interrupt during put()");
        }
    }

    @Override
    public void run() {
        try {
            while (rockets.isEmpty()) {
                LiftOff liftOff = null;
                liftOff = rockets.take();
                liftOff.run();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("exiting liftoff runner");
    }
}
