package com.example.rxjavastudy.java.thread.cancel;

import java.util.Random;

public class Count {
    private int count = 0;
    private Random random = new Random(47);

    public synchronized int increment() {//如果不加synchronized则会导致数据错误
        int temp = count;
        if (random.nextBoolean())
            Thread.yield();
        return count = ++temp;
    }

    public synchronized int value() {
        return count;
    }


}
