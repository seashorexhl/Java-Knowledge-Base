package com.xhl.C02_Architecture_Design.DesignPattern.C01_CreationalPatterns.SingletonPattern;

/**
 * @Author: xhl
 * @Date: 2026-04-17 12:37
 * @Description: 单列模式
 */
public class DemoSingleThread  {

    public static void main(String[] args) {
        System.out.println("If you see the same value, then singleton was reused (yay!)" + "\n" +
                "If you see different values, then 2 singletons were created (booo!!)" + "\n\n" +
                "RESULT:" + "\n");
        Singleton singleton = Singleton.getInstance("FOO");
        Singleton anotherSingleton = Singleton.getInstance("BAR");
        System.out.println(singleton.value);
        System.out.println(anotherSingleton.value);
    }
}
