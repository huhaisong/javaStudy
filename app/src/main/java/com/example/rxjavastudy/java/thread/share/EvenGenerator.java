package com.example.rxjavastudy.java.thread.share;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

public class EvenGenerator extends IntGenerator {
    private int currentEventValue = 0;

    @Override
    public int next() {
        ++currentEventValue;
        Thread.yield();
        ++currentEventValue;
        return currentEventValue;
    }

    public static void main(String[] args) {
      /*  Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                System.out.println("val = " + msg.arg1);
            }
        };*/
        EvenChecker.test(new EvenGenerator(), null);
    }
}
