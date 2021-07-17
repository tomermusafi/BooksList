package com.musafi.bookslist.models;

public class MyRBG {
    private int red;
    private int green;
    private int blue;

    public MyRBG() {
    }

    public MyRBG(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public MyRBG setRed(int red) {
        this.red = red;
        return this;
    }

    public int getGreen() {
        return green;
    }

    public MyRBG setGreen(int green) {
        this.green = green;
        return this;
    }

    public int getBlue() {
        return blue;
    }

    public MyRBG setBlue(int blue) {
        this.blue = blue;
        return this;
    }
}
