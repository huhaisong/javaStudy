package com.example.rxjavastudy.java.thread.new_class.DelayQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class DelayTask implements Runnable, Delayed {
    private static int count = 0;
    private final int id = count++;
    private final int delta;
    private final long trigger;
    protected static List<DelayTask> delayTasks = new ArrayList<>();

    public DelayTask(int delta) {
        this.delta = delta;
        trigger = System.nanoTime() + NANOSECONDS.convert(delta, MILLISECONDS);
        delayTasks.add(this);
    }

    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger - System.nanoTime(), NANOSECONDS);
    }

    public int compareTo(Delayed arg) {
        DelayTask that = (DelayTask) arg;
        if (trigger < that.trigger) return -1;
        if (trigger > that.trigger) return 1;
        return 0;
    }

    @Override
    public void run() {
        System.out.println(this + " ");
    }

    @Override
    public String toString() {
        return "DelayTask{" +
                "id=" + id +
                ", delta=" + delta +
                '}';
    }

    public String summary(){
        return "("+id +":"+delta+")";
    }

    public static class EndSentinel extends DelayTask {
        private ExecutorService executorService ;

        public EndSentinel(int delta, ExecutorService executorService) {
            super(delta);
            this.executorService = executorService;
        }

        @Override
        public void run() {
            for (DelayTask delayTask : delayTasks) {
                System.out.println(delayTask.summary()+" ");
            }
            System.out.println(this+"calling shutdownNow()");
            executorService.shutdownNow();
        }
    }
}
