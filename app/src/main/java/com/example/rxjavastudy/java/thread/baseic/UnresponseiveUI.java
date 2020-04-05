package com.example.rxjavastudy.java.thread.baseic;

import java.io.IOException;

public class UnresponseiveUI {

    private volatile double d = 1;

    public UnresponseiveUI( ) throws IOException {
       while (d>0){
           d += (Math.PI+Math.E) /d;
       }
       System.in.read();
    }
}
