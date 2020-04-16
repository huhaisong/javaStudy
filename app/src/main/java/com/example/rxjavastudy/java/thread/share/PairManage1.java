package com.example.rxjavastudy.java.thread.share;

import com.example.rxjavastudy.java.thread.share.PairManager;

class PairManage1 extends PairManager {

    @Override
    public synchronized void increment() {
        p.increamentX();
        Thread.yield();
        p.increamentY();
        store(getP());
    }
}
