package com.example.rxjavastudy.java.thread.new_class.DelayQueue;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DelayQueueDemo  {

    public static void main(String[] args) {
        Random random = new Random(47);
        ExecutorService executorService = Executors.newCachedThreadPool();
        DelayQueue<DelayTask> delayTasks = new DelayQueue<DelayTask>();
        for (int i = 0; i < 20; i++) {
            delayTasks.put(new DelayTask(random.nextInt(5000)));
        }
        delayTasks.add(new DelayTask.EndSentinel(5000,executorService));
        executorService.execute(new DelayedTaskConsumer(delayTasks));
    }
}
