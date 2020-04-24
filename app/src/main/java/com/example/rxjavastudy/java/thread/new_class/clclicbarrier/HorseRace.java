package com.example.rxjavastudy.java.thread.new_class.clclicbarrier;

import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HorseRace {
    static final int FINISH_LINE = 75;
    private ArrayList<Horse> horses = new ArrayList<>();
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private CyclicBarrier cyclicBarrier;

    public HorseRace(int mHorse, final int pause) {
        cyclicBarrier = new CyclicBarrier(mHorse, new Runnable() { //cyclicBarrier在所有的都执行完了之后会执行runnable里面的的代码  第一个参数为边界限定数
            @Override
            public void run() {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < FINISH_LINE; i++) {
                    stringBuilder.append("=");
                }
                System.out.println(stringBuilder);
                for (Horse horse : horses) {
                    System.out.println(horse.tracks());
                }
                for (Horse horse : horses) {
                    if (horse.getStrides() >= FINISH_LINE) {
                        System.out.println(horse + "win");
                        executorService.shutdownNow();
                        return;
                    }
                }
                try {
                    Thread.sleep(pause);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        for (int i = 0; i < mHorse; i++) {
            Horse horse = new Horse(cyclicBarrier);
            horses.add(horse);
            executorService.execute(horse);
        }
    }

    public static void main(String[] args) {
        int mHorses = 7;
        int pause = 200;
        new HorseRace(mHorses, pause);
    }
}
