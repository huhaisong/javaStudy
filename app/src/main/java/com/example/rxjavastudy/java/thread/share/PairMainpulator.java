package com.example.rxjavastudy.java.thread.share;


class PairMainpulator implements Runnable {
    private PairManager pairManager;

    public PairMainpulator(PairManager pairManager) {
        this.pairManager = pairManager;
    }

    @Override
    public void run() {
        while (true) {
            pairManager.increment();
        }
    }

    @Override
    public String toString() {
        return "PairMainpulator{" +
                "pair = " + pairManager.getP() +
                "pairManager=" + pairManager.checkCounter.get() +
                '}';
    }
}