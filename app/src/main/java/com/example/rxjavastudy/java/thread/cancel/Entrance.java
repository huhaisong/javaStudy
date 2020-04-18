package com.example.rxjavastudy.java.thread.cancel;

import java.util.ArrayList;
import java.util.List;

public class Entrance implements Runnable {
    private static Count count = new Count();
    private static List<Entrance> entrances = new ArrayList<>();
    private static volatile boolean canceled = false;
    private int number = 0;
    private final int id;

    public static void cancel() {
        canceled = true;
    }

    public Entrance(int id) {
        this.id = id;
        entrances.add(this);
    }

    @Override
    public void run() {
        while (!canceled) {
           {
                number++;
            }
                System.out.println(this + "total :" + count.increment());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("stop " + this);
    }

    public  int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Entrance{" +
                "number=" + getNumber() +
                ", id=" + id +
                '}';
    }

    public static int getCountValue() {
        return count.value();
    }

    public static int sumEntrances() {
        int sum = 0;
        for (Entrance item : entrances) {
            sum += item.getNumber();
        }
        return sum;
    }
}
