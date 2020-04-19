package com.example.rxjavastudy.java.thread.coordination.restaurant;

public class Chef implements Runnable {

    private Restaurant restaurant;
    private int count = 0;

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {//以防止在sleep得时候别的线程调用notify
                    while (restaurant.meal != null) {//防止别的chef已经做好这个meal导致制作相同得meal
                        wait();
                    }
                    if (++count == 10) {
                        System.out.println("out of food, close");
                        restaurant.executorService.shutdownNow();
                    }
                    System.out.println("Chef doing!");
                    Thread.sleep(100);
                    synchronized (restaurant.waiterPerson) {//等待服务员上菜完毕
                        restaurant.meal = new Meal(count);
                        restaurant.waiterPerson.notifyAll();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("chef interrupt");
        }
    }
}
