package com.xhl.Architecture_Design.DesignPattern.CreationalPatterns.SingletonPattern;

/**
 * @Author: xhl
 * @Date: 2026-06-12 21:55
 * @Description: 饿汉式（Eager Initialization）
 */
public class HungrySingleton {
    // 类加载时立即初始化实例
    private static final HungrySingleton INSTANCE = new HungrySingleton();

    // 私有化构造方法，禁止外部通过 new 创建对象
    private HungrySingleton() {}

    // 提供全局公共的静态方法获取实例
    public static HungrySingleton getInstance() {
        return INSTANCE;
    }
}