package com.example.rxjavastudy.java.thread.deadlock;

import java.util.Random;

public class Philosopher implements Runnable {
    private Chopstick left, right;
    private final int id;
    private final int ponderFactor;
    private Random rand = new Random();


    public Philosopher(Chopstick left, Chopstick right, int id, int ponderFactor) {
        this.left = left;
        this.right = right;
        this.id = id;
        this.ponderFactor = ponderFactor;
    }

    private void pause() {
        if (ponderFactor == 0) return;
        try {
            Thread.sleep(rand.nextInt( 100));
        } catch (InterruptedException e) {
            System.out.println("Philosopher:" + id + "exit because of interrupted");
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + "thinking");
                pause();
                System.out.println(this + " grabbing right");
                right.take();
                Thread.sleep(10);
                System.out.println(this + " grabbing left");
                left.take();
                System.out.println(this + "eating");
                pause();
                right.drop();
                left.drop();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Philosopher{" +
                "id=" + id +
                '}';
    }
}
