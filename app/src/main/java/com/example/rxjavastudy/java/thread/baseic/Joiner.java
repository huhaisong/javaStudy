package com.example.rxjavastudy.java.thread.baseic;

public class Joiner extends Thread {
    private Sleeper sleeper;

    public Joiner(String name, Sleeper sleeper) {
        this.sleeper = sleeper;
        start();
    }

    @Override
    public void run() {
        try {
            sleeper.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf(this + " join complete," + sleeper + "\n");
    }

    public static void main(String[] args) throws InterruptedException {
        Sleeper sleeper = new Sleeper("sleepy", 1500);
        Sleeper grumpy = new Sleeper("grumpy", 1500);
        Thread.currentThread().sleep(1000);
        Joiner dopey = new Joiner("Dopey", sleeper);
        Joiner doc = new Joiner("doc", grumpy);//joiner 和sleep 一同结束证明sleeper已经
        grumpy.interrupt();//这属于在一个线程调用其他线程，会报错
    }
}
