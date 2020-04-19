package com.example.rxjavastudy.java.thread.coordination.restaurant;

public class WaiterPerson implements Runnable {
    private Restaurant restaurant;
    public boolean isBusyBoy = false;

    public WaiterPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null || isBusyBoy) {
                        wait();
                    }
                }
                System.out.println("waitperson got " + restaurant.meal);
                synchronized (restaurant.busBoy) {
                    isBusyBoy = true;
                    restaurant.busBoy.notifyAll(); //通知busboy 用餐
                }
            }
        } catch (InterruptedException e) {
            System.out.println("waiter person interrupted");
        }
    }
}
