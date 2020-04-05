package com.example.rxjavastudy.java.thread.baseic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class HandlerThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this + " create a new Thread");
        Thread t = new Thread(r);
        System.out.println("created " + t);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("eh = " + t.getUncaughtExceptionHandler());
        return t;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool(new HandlerThreadFactory());
        executorService.execute(new ExceptionThread());
    }
}
