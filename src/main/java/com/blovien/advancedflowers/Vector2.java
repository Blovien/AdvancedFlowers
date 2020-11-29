package com.blovien.advancedflowers;

import java.util.Iterator;

public class Vector2 {

    private int x;
    private int y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static boolean checkYBounds(int result) {
        return result >= 0 && result <= 5;
    }

    public static boolean checkXBounds(int result) {
        return result >= 0 && result <= 8;
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
        return this.x + (54-this.y*9);
    }

    public void sub(int x, int y) {
        subX(x);
        subY(y);
    }

    public void subY(int y) {
        if (checkYBounds(this.y - y)) {
            this.y -= y;
        }
    }

    public void subX(int x) {
        if (checkXBounds(this.x - x)) {
            this.x -= x;
        }
    }

    public void add(int x, int y) {
        addX(x);
        addY(y);
    }

    public void addY(int y) {
        if (checkYBounds(this.y + y)) {
            this.y += y;
        }
    }

    public void addX(int x) {
        if (checkXBounds(this.x + x)) {
            this.x += x;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
