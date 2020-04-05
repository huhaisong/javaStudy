package com.example.rxjavastudy.java.thread.baseic;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        Thread.currentThread().sleep(100);
        return "result of taskwithresult = " + id + ",thread name = " + Thread.currentThread().getName() + "\n";
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new LiftOff());
        }
        executorService.shutdown();
        executorService = Executors.newFixedThreadPool(5);
        ArrayList<Future<String>> resul = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            resul.add(executorService.submit(new TaskWithResult(i)));
        }
        for (Future<String> item : resul) {
            try {
                if (item.isDone())
                    System.out.printf(item.get()); //如果直接使用item.get()当前调用线程将会阻塞，直到item线程执行完成
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }
}
