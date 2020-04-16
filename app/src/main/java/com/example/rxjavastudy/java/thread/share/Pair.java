package com.example.rxjavastudy.java.thread.share;

public class Pair {
    private int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair() {
        this(0, 0);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void increamentX() {
        x++;
    }

    public void increamentY() {
        y++;
    }


    class PairNotEqualException extends RuntimeException {
        public PairNotEqualException() {
            super("pair not equal: " + Pair.this);
        }
    }

    public void checkState() {
        if (x != y) {
            System.out.println("x!= y" + this);
//            throw new PairNotEqualException();
        }
    }


    @Override
    public String toString() {
        return "Pair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
