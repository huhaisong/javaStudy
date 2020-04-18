package com.example.rxjavastudy.java.thread.cancel;

import java.io.IOException;
import java.io.InputStream;

public class IoBlocked implements Runnable {
    private InputStream in;

    public IoBlocked(InputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            System.out.println("wait for read");
            in.read();
        } catch (IOException e) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted from blocked I/O");
            } else
                e.printStackTrace();
        }
        System.out.println("exiting io block thread!");
    }


}
