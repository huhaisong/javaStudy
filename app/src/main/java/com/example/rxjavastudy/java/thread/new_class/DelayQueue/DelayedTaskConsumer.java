package com.example.rxjavastudy.java.thread.new_class.DelayQueue;

import java.util.concurrent.DelayQueue;

public class DelayedTaskConsumer implements Runnable {
    private DelayQueue<DelayTask> delayTasks;

    public DelayedTaskConsumer(DelayQueue<DelayTask> delayTasks) {
        this.delayTasks = delayTasks;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                delayTasks.take().run();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finished DelayedTaskConsumer");
    }
}
