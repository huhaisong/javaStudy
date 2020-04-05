package com.example.rxjavastudy.java.thread.baseic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new LiftOff());
        }
        executorService.shutdown();
//        executorService.execute(new LiftOff());  //在执行了shutdown之后不再允许使用execute方法次对象不能再执行新任务
    }
}
