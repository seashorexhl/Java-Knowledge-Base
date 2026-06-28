package com.xhl.Java_Core.java17.SealedClasses;

/**
 * @Author: xhl
 * @Date: 2026-04-06 00:14
 * @Description:
 */
public final class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
