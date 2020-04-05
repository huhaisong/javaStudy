package com.example.rxjavastudy.java.thread.baseic;

public class Sleeper extends Thread {
    private int duration;

    public Sleeper(String name, int duration) {
        this.duration = duration;
        start();
    }

    @Override
    public void run() {
        //注意：不能够抛出异常，因为线程之间不能传递抛出的异常，可以用ExecutorService代替thread
        try {
            System.out.printf(getName() + "start sleep\n");
            sleep(duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
