package com.example.rxjavastudy.java.thread.baseic;

public class SimpleThread extends Thread {
    private int countDown = 5;

    private static int threadCount = 0;

    public SimpleThread() {
        super(Integer.toString(++threadCount));
        start();
    }

    @Override
    public String toString() {
        return getName() + " {" +
                "countDown=" + countDown +
                '}';
    }

    public void run() {
        while (true) {
            System.out.print(this + "\n");
            if (--countDown == 0) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new SimpleThread();
        }
    }
}
