package com.example.rxjavastudy.java.thread.share;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CriticalSelection {
    static void testApproaches(PairManager pairManager1, PairManager pairManager2) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        PairMainpulator pairMainpulator1 = new PairMainpulator(pairManager1);
        PairMainpulator pairMainpulator2 = new PairMainpulator(pairManager2);
        PairChecker pairChecker1 = new PairChecker(pairManager1);
        PairChecker pairChecker2 = new PairChecker(pairManager2);
        executorService.execute(pairMainpulator2);
        executorService.execute(pairMainpulator1);
        executorService.execute(pairChecker1);
        executorService.execute(pairChecker2);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("pm1:" + pairMainpulator1 + ",pm2:" + pairMainpulator2);
        System.exit(0);
    }

    public static void main(String[] args) {
        PairManager pairManager1 = new PairManage1();
        PairManager pairManager2 = new PairManage2();
        testApproaches(pairManager1, pairManager2);
    }
}