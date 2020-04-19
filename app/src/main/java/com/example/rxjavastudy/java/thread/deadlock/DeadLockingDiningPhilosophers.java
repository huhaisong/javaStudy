package com.example.rxjavastudy.java.thread.deadlock;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeadLockingDiningPhilosophers {
    public static void main(String[] args) throws IOException, InterruptedException {
        int ponder = 5;
        int size = 5;
        ExecutorService executorService = Executors.newCachedThreadPool();
        Chopstick[] chopsticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            chopsticks[i] = new Chopstick();
        }
        for (int i = 0; i < size; i++) {
            Thread.sleep(100);
            executorService.execute(new Philosopher(chopsticks[i], chopsticks[(i + 1) % size], i, ponder));
        }
        System.out.println("press enter to stop!");
        System.in.read();
        executorService.shutdownNow();
    }
}
