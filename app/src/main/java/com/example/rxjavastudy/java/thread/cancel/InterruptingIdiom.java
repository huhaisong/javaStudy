package com.example.rxjavastudy.java.thread.cancel;

public class InterruptingIdiom  {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Blocked3());  //如果打断出现在正在睡眠中，那么会直接异常并退出。如果是在计算中（睡眠以后）会先执行完计算退出循环，然后再到循环判断处判断，退出循环
        t.start();
        Thread.sleep(1030);
        t.interrupt();
    }
}
