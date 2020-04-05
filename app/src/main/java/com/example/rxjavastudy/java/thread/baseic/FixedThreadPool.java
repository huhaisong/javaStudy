package com.example.rxjavastudy.java.thread.baseic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService  = Executors.newFixedThreadPool(5);//预先分配线程 固定线程池的线程数量
        for (int i = 0; i < 5; i++) {
            executorService.execute(new LiftOff());
        }
        executorService.shutdown();
    }
}
