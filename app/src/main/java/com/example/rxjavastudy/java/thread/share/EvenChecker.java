package com.example.rxjavastudy.java.thread.share;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable {
    private IntGenerator generator;
    private final int id;
    private Handler handler;

    public EvenChecker(IntGenerator generator, int id, Handler handler) {
        this.generator = generator;
        this.id = id;
        this.handler = handler;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()) {
            int val = generator.next();
//            System.out.println(val + "even !" + Thread.currentThread());
            if (val % 2 != 0) {
                System.out.println(val + "not even !" + Thread.currentThread());
                generator.cancel();
            }
        }
        System.out.println(Thread.currentThread() + "will finished");
    }

    public static void test(IntGenerator intGenerator, int count,Handler handler) {
        System.out.println("Press control-c to exit");
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            executorService.execute(new EvenChecker(intGenerator, count,handler));
        }
        executorService.shutdown();
    }

    public static void test(IntGenerator intGenerator,Handler handler) {
        test(intGenerator, 10,handler);
    }
}
