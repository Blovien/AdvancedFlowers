package com.blovien.advancedflowers.utils;

public class Vector2 {

    private int x;
    private int y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2 vectorFromIndex(int index) {
        for (int i = 5, tempX; i >= 0; i--) {
            if ((tempX = index-i*9) >= 0) {
                return new Vector2(tempX, 5-i);
            }
        }

        return new Vector2(-1, -1);
    }

    public static int toIndex(int x, int y) {
        return new Vector2(x, y).toIndex();
    }

    public int toIndex() {
        return this.x + (54-(this.y+1)*9);
    }

    public void sub(int x, int y) {
        subX(x);
        subY(y);
    }

    public void subY(int y) {
        this.y -= y;
    }

    public void subX(int x) {
        this.x -= x;
    }

    public void add(int x, int y) {
        addX(x);
        addY(y);
    }

    public void addY(int y) {
        this.y += y;
    }

    public void addX(int x) {
        this.x += x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
