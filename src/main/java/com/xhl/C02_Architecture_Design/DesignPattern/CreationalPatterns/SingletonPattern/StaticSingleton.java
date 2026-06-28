package com.xhl.Architecture_Design.DesignPattern.CreationalPatterns.SingletonPattern;

/**
 * @Author: xhl
 * @Date: 2026-06-12 22:00
 * @Description:  静态内部类
 */
public class StaticSingleton {
    public static StaticSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    // 静态内部类：外部类加载时不会加载，只有调用 getInstance() 时才会加载
    private static class SingletonHolder {
        private static final StaticSingleton INSTANCE = new StaticSingleton();
    }


}
