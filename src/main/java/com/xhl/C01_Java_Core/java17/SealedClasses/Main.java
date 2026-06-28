package com.xhl.C01_Java_Core.java17.SealedClasses;

/**
 * @Author: xhl
 * @Date: 2026-04-06 00:12
 * @Description: 演示密封类主函数
 * 密封类允许类之间的继承关系更加明确和受控。密封类只能被指定的其他类继承。
 */
public class Main {
    public static void main(String[] args) {
        Shape shape = new Circle(5);
        System.out.println("Shape is a circle with radius: " + ((Circle) shape).getRadius());

        shape = new Rectangle(4, 6);
        System.out.println("Shape is a rectangle with width: " + ((Rectangle) shape).getWidth() + " and height: " + ((Rectangle) shape).getHeight());
    }
}
