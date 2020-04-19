package com.example.rxjavastudy.java.thread.coordination.blockqueue;

import com.example.rxjavastudy.java.thread.baseic.LiftOff;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class TestBlockingQuenes {
    static void getKey() {
        try {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void getKey(String message) {
        System.out.println(message);
        getKey();
    }

    static void test(String message, BlockingQueue<LiftOff> queue) {
        System.out.println(message+" start");
        LiftOffRunner liftOffRunner = new LiftOffRunner(queue);
        Thread thread = new Thread(liftOffRunner);
        thread.start();
        for (int i = 0; i < 5; i++) {
            liftOffRunner.add(new LiftOff(5));
        }
        getKey("press enter " + message);
        thread.interrupt();
        System.out.println("finished " + message + " test");
    }

    public static void main(String[] args) {
        test("LinkedBlockingQueue", new LinkedBlockingQueue<LiftOff>());
        test("ArrayBlockingQueue", new ArrayBlockingQueue<LiftOff>(3));
        test("SynchronousQueue", new SynchronousQueue<LiftOff>());
    }
}
