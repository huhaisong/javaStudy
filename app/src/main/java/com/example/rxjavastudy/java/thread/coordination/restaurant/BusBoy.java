package com.example.rxjavastudy.java.thread.coordination.restaurant;

public class BusBoy implements Runnable {
    Restaurant restaurant;

    public BusBoy(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null) {
                        wait();
                    }
                }
                System.out.println("BusBoy doing" + restaurant.meal);
                Thread.sleep(100);
                synchronized (restaurant.chef) {//如果chef正拿着锁即sleep状态则会等到chef完成之后再次去唤醒chef唤醒之后chef将再次拿到锁进入sleep。
                    restaurant.meal = null;
                    restaurant.waiterPerson.isBusyBoy = false;
                    restaurant.chef.notifyAll();  //用餐完毕，通知初始做菜
                }
            }
        } catch (InterruptedException e) {
            System.out.println("BusBoy interrupt");
        }
    }
}
