package com.xhl.C02_Architecture_Design.DesignPattern.C01_CreationalPatterns.SingletonPattern;

/**
 * @Author: xhl
 * @Date: 2026-04-17 12:54
 * @Description: 单例模式 静态内部类
 */
/**
 *  总结与选型建议
 * 饿汉式：适合实例创建开销较小，且在程序运行期间一定会被使用的场景（如简单的全局配置管理器、轻量级日志工具类）。
 * 懒汉式（双重检查锁/静态内部类）：适合实例创建成本较高（如数据库连接池、复杂的 API 客户端），或者在整个生命
 * 周期中可能不会被使用的场景。这也是企业开发中最常用的延迟加载方案。
 * */
public final class Singleton {

    private static Singleton instance;
    public String value;

    private Singleton(String value) {
        // The following code emulates slow initialization.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.value = value;
    }

    public static Singleton getInstance(String value) {
        if (instance == null) {
            instance = new Singleton(value);
        }
        return instance;
    }
}