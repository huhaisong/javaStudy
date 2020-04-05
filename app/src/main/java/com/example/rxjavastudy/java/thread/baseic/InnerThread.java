package com.example.rxjavastudy.java.thread.baseic;

import androidx.annotation.NonNull;

public class InnerThread {
    private int countDown;
    private Inner inner;

    private class Inner extends Thread {
        public Inner(String name) {
            super(name);
            start();
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(this);
                    if (--countDown == 0)
                        return;
                    sleep(100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        @NonNull
        @Override
        public String toString() {
            return getName() + ":" + countDown;
        }
    }

    private class Inner2 {
        private int countDown = 5;
        private Thread t;

        public Inner2(String name) {
            t = new Thread(name) {
                @Override
                public void run() {
                    try {
                        while (true) {
                            System.out.println(this);
                            if (--countDown == 0) return;
                            sleep(10);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @NonNull
                @Override
                public String toString() {
                    return getName() + ":" + countDown;
                }
            };
            t.start();
        }
    }

    class InnerRunable {
        private int countDown = 5;
        private Inner inner;

        private class Inner implements Runnable {
            Thread t;

            public Inner(String name) {
                t = new Thread(this, name);
                t.start();
            }

            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println(this);
                        if (--countDown == 0)
                            return;
                        Thread.sleep(100);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @NonNull
            @Override
            public String toString() {
                return t.getName() + ":" + countDown;
            }
        }

        public InnerRunable(String name) {
            inner = new Inner(name);
        }
    }


}
