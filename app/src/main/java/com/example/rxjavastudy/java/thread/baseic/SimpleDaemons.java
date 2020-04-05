package com.example.rxjavastudy.java.thread.baseic;

public class SimpleDaemons implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(100);
                System.out.printf(Thread.currentThread() + " \n");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true); //前台进程死掉之后直接终止进程
            daemon.start();
        }
        System.out.printf("all daemons started");
//        TimeUnit.MICROSECONDS.sleep(1300);
        Thread.sleep(190);
    }
}
