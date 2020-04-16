package com.example.rxjavastudy.java.thread.share;

class PairManage2 extends PairManager {
    @Override
    public void increment() {
        Pair temp;
        synchronized (this) {
            p.increamentY();
            Thread.yield();
            p.increamentX();
            temp = getP();
        }
        store(temp);
    }
}