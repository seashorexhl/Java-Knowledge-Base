package com.xhl.C01_Java_Core.java17.JVM;

/**
 * @Author: xhl
 * @Date: 2026-06-24 21:04
 * @Description: 破坏双亲委派（特殊场景）
 */
// 类名必须改变，且通常建议指定 parent
public class MySpecialClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // 1. 检查是否已加载
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
                try {
                    // 注意：这里我们故意不委托给 parent，实现隔离
                    // 但要注意，像 java.lang.Object 这种核心类，如果不委托，可能会报错或导致严重问题
                    if (name.startsWith("java.")) {
                        // 核心类还是得交给启动类加载器
                        c = getSystemClassLoader().loadClass(name);
                    } else {
                        // 自己加载
                        c = findClass(name);
                    }
                } catch (ClassNotFoundException e) {
                    // 如果找不到，再尝试让父类去找（可选策略）
                    c = getParent().loadClass(name);
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // ... 实现具体的字节码读取逻辑 ...
        return super.findClass(name);
    }
}
