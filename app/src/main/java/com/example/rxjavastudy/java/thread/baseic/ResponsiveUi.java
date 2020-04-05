package com.example.rxjavastudy.java.thread.baseic;

import java.io.IOException;

public class ResponsiveUi extends Thread {

    private static volatile double d = 1;

    public ResponsiveUi() {
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        while (d > 0) {
            d += (Math.PI + Math.E) / d;
        }
    }

    public static void main(String[] args) throws IOException {
        new ResponsiveUi();
        System.in.read();
        System.out.println(d);
    }
}
