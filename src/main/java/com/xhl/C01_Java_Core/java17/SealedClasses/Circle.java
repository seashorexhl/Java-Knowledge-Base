package com.xhl.C01_Java_Core.java17.SealedClasses;

/**
 * @Author: xhl
 * @Date: 2026-04-06 00:13
 * @Description:
 */
public final class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}