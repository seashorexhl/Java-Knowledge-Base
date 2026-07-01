package com.xhl.C02_Architecture_Design.DesignPattern.C01_CreationalPatterns.SingletonPattern;

/**
 * @Author: xhl
 * @Date: 2026-06-12 21:57
 * @Description: 懒汉式
 */
public class LazySingleton {
    // volatile 关键字：禁止指令重排序，保证多线程下的可见性
    private static volatile LazySingleton instance;

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        // 第一次检查：避免不必要的同步，实例已存在直接返回
        if (instance == null) {
            synchronized (LazySingleton.class) {
                // 第二次检查：防止多线程同时进入第一次检查后重复创建
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
