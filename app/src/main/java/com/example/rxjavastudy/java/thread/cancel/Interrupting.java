package com.example.rxjavastudy.java.thread.cancel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Interrupting {
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    static void test(Runnable runnable) throws InterruptedException {
        Future<?> future = executorService.submit(runnable);
        Thread.currentThread().sleep(100);
        System.out.println("interrupting " + runnable.getClass().getName());
        future.cancel(true);
        System.out.println("interrupt send to " + runnable.getClass().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        //只有睡眠线程才能抛出打断异常，资源和锁都不会因为打断而异常
        test(new SleepBlocked());
        test(new IoBlocked(System.in));
        test(new SynchronizedBlocked());
        Thread.sleep(3000);
        System.out.println("exit(0)");
        System.exit(0);
    }
}
