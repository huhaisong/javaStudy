package com.example.rxjavastudy.java.thread.cancel;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CloseResource {
    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(8080);
        InputStream inputStream = new Socket("localhost", 8080).getInputStream();
        executorService.execute(new IoBlocked(System.in));
        executorService.execute(new IoBlocked(inputStream));
        Thread.sleep(100);
        System.out.println("shutdown all thread");
        executorService.shutdown();
        Thread.sleep(1000);
        System.out.println("close socket");
        inputStream.close();
        Thread.sleep(1000);
        System.out.println("close system.in");
        System.in.close();
    }
}
