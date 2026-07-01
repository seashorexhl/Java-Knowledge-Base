package com.xhl.C01_Java_Core.EffectiveJava.C02CreatingDestroyingObjects;

/**
 * @Author: xhl
 * @Date: 2026-07-01 07:57
 * @Description:  全局配置管理器
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 【条目4】利用私有构造器防止类被实例化
 * 这是一个纯工具/管理类，不需要被 new，直接私有化构造器并抛异常。
 */
public final class GlobalConfigManager {

    // 【条目4】防止继承
    private GlobalConfigManager() {
        throw new AssertionError("Utility class cannot be instantiated!");
    }

    /**
     * 【条目1】用静态工厂方法代替构造器
     * 见名知意，且可以返回缓存/单例，或者未来返回子类。
     */
    public static GlobalConfigManager getInstance() {
        // 实际上返回的是枚举单例的内部状态，对外暴露统一入口
        return new GlobalConfigManager(); // 这里为了演示，如果是纯单例可以直接返回枚举
    }

    /**
     * 【条目5】优先考虑依赖注入来连接资源
     * 不要在这里面 new DatabaseConnection()，而是通过参数传进来。
     */
    public static void loadFromDatabase(Map<String, String> dbConnection) {
        // 使用外部传入的资源，而不是内部硬编码创建
        System.out.println("Loading config from DB: " + dbConnection);
    }

    /**
     * 【条目6】避免创建不必要的对象
     * 不要写 new String("test")，直接复用已有对象。
     */
    public static boolean isProdEnv() {
        String env = ConfigHolder.INSTANCE.getConfigMap().get("env");
        // 错误写法：return new String("prod").equals(env);
        // 正确写法：直接复用字符串常量
        return "prod".equals(env);
    }

    /**
     * 【条目9】try-with-resources 优于 try-finally
     * 自动管理资源关闭，避免资源泄漏，代码极其简洁。
     */
    public static void loadFromFile(String filePath) {
        // 只要实现了 AutoCloseable/Closeable，就会自动 close
        try (var reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Read file failed: " + e.getMessage());
        }
    }

    /**
     * 【条目7】清除过期的对象引用
     * 当缓存或集合中的对象不再需要时，主动置空或 remove，防止内存泄漏。
     */
    public static void clearExpiredConfig(String key) {
        Map<String, String> map = ConfigHolder.INSTANCE.getConfigMap();
        if (map.containsKey(key)) {
            map.remove(key); // 清除引用，让 GC 可以回收
            System.out.println("Cleared expired config: " + key);
        }
    }

    // 【条目3】利用枚举强化 Singleton 属性
    // 枚举单例：绝对线程安全、防反射攻击、防序列化破坏
    private enum ConfigHolder {
        INSTANCE;
        private final Map<String, String> configMap = new ConcurrentHashMap<>();

        Map<String, String> getConfigMap() { return configMap; }
    }

    /**
     * 【条目2】多参数构造时优先使用 Builder
     * 避免“伸缩构造器反模式”，让参数设置清晰明了。
     */
    public static class ConfigBuilder {
        private String env;
        private int timeout;
        private String dbUrl;

        public ConfigBuilder setEnv(String env) { this.env = env; return this; }
        public ConfigBuilder setTimeout(int timeout) { this.timeout = timeout; return this; }
        public ConfigBuilder setDbUrl(String dbUrl) { this.dbUrl = dbUrl; return this; }

        public Map<String, String> build() {
            // 校验必填项
            Objects.requireNonNull(env, "Environment must not be null");
            Map<String, String> map = new ConcurrentHashMap<>();
            map.put("env", env);
            map.put("timeout", String.valueOf(timeout));
            map.put("dbUrl", dbUrl);
            return map;
        }
    }

    // 【条目8】避免使用终结方法（finalize）
    // 绝对不要重写 protected void finalize() 方法！
    // 如果需要清理资源，请实现 Closeable 接口，让调用方使用 try-with-resources。
}