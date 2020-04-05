package com.example.rxjavastudy.java;

public class MainThread {
    public static void main(String[] args) {
//        Thread thread= new Thread(new LiftOff());
//        thread.start();  //start迅速的返回了，并不等待。
//        System.out.printf("waiting for the thread");

        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
        }
        System.out.printf("waiting for liftoff!\n");
    }
}
