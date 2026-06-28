package com.xhl.Java_Core.java17.JVM;

/**
 * @Author: xhl
 * @Date: 2026-06-24 21:02
 * @Description: 继承并重写 findclass
 * 自定义类加载逻辑（例如热部署、加密解密类文件、从网络加载类等)
 */
import java.io.*;

public class MyCustomClassLoader extends ClassLoader {

    private String classPath; // 假设这是你的类文件存放路径

    public MyCustomClassLoader(String classPath) {
        this.classPath = classPath;
    }

    /**
     * 核心修复点：
     * 1. 不要重写 loadClass (除非你要破坏双亲委派模型)
     * 2. 应该重写 findClass
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            // 1. 获取类的字节码数据
            byte[] data = loadByteData(name);

            if (data == null) {
                throw new ClassNotFoundException("类文件未找到: " + name);
            }

            // 2. 调用 defineClass 将字节数组转换为 Class 对象
            // 这是一个 native 方法，也是类加载的最终步骤
            return defineClass(name, data, 0, data.length);

        } catch (IOException e) {
            throw new ClassNotFoundException("加载类出错: " + name, e);
        }
    }

    // 模拟读取字节码文件的辅助方法
    private byte[] loadByteData(String name) throws IOException {
        // 将包名 com.example.Test 转换为路径 com/example/Test.class
        String path = classPath + File.separatorChar
                + name.replace('.', File.separatorChar) + ".class";

        File file = new File(path);
        if (!file.exists()) return null;

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             FileInputStream fis = new FileInputStream(file)) {

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        }
    }
}