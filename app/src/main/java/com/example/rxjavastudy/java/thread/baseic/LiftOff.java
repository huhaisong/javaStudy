package com.example.rxjavastudy.java.thread.baseic;

public class LiftOff implements Runnable {
    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;  //初始化后不再变化，并且保存在内存中，所以每初始化一次，这个值就会变化一次 每次进程运行都重新初始化

    public LiftOff() {
    }


    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return Thread.currentThread().getName()+"#"  + id + "(" +
                (countDown > 0 ? countDown : "LiftOff!") + ")," ;
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(status());
            Thread.yield();
        }
    }
}
