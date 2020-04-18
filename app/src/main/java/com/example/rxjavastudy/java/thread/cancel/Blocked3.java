package com.example.rxjavastudy.java.thread.cancel;

public class Blocked3 implements Runnable {
    private volatile double d = 0.0;

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                NeedCleanup needCleanup = new NeedCleanup(1);
                try {
                    System.out.println("sleeping");
                    Thread.sleep(1000);//在这里被打断后面的不会再被执行
                    NeedCleanup needCleanup1 = new NeedCleanup(2);
                    try {
                        System.out.println("calculating");
                        for (int i = 0; i < 250000000; i++) {
                            d = d + (Math.E + Math.PI) / d;
                            for (int j = 0; j < 25; j++) {
                                d = d + (Math.E + Math.PI) / d;
                            }
                        }
                        System.out.println("finished time-consuming operation = "+d);
                    } finally {
                        needCleanup1.cleanup();
                    }
                } finally {
                    needCleanup.cleanup();
                }
            }
            System.out.println("exiting via while test");
        } catch (InterruptedException e) {
            System.out.println("exiting via interruptedException");
        }
    }


}
