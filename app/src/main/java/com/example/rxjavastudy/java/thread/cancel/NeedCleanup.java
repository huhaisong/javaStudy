package com.example.rxjavastudy.java.thread.cancel;

public class NeedCleanup {
    private final int id;

    public NeedCleanup(int id) {
        this.id = id;
        System.out.println("NeedCleanup " + id);
    }

    public void cleanup() {
        System.out.println("Cleaning up " + id);
    }


}
