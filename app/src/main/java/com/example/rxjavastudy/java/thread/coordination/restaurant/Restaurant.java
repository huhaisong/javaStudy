package com.example.rxjavastudy.java.thread.coordination.restaurant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Restaurant {
    Meal meal;
    ExecutorService executorService = Executors.newCachedThreadPool();
    WaiterPerson waiterPerson = new WaiterPerson(this);
    BusBoy busBoy = new BusBoy(this);
    Chef chef = new Chef(this);

    public Restaurant() {
        executorService.execute(chef);
        executorService.execute(waiterPerson);
        executorService.execute(busBoy);
    }

    public static void main(String[] args) {
        new Restaurant();
    }
}
