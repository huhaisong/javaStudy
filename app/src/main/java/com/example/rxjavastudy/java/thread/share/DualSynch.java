package com.example.rxjavastudy.java.thread.share;

/**
 * 这两个同步是相互独立的。
 * 如果把同步对象改成DualSynch.this则两个同步相互制约从而形成阻塞
 */
public class DualSynch {
    private Object syncObject = new Object();

    public synchronized void f() {
        for (int i = 0; i < 5; i++) {
            System.out.println("f()" + Thread.currentThread());

            Thread.yield();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public synchronized void h() {
        for (int i = 0; i < 5; i++) {
            System.out.println("h()" + Thread.currentThread());

            Thread.yield();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void g() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println("g()" + Thread.currentThread());
                Thread.yield();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        final DualSynch ds = new DualSynch();
        new Thread() {
            @Override
            public void run() {
                ds.g();
            }
        }.start();
        ds.h();
    }
}
