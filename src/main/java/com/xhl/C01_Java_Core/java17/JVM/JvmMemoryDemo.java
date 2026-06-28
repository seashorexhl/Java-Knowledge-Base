package com.xhl.Java_Core.java17.JVM;

/**
 * @Author: xhl
 * @Date: 2026-06-24 20:45
 * @Description: 堆（Heap）与虚拟机栈（VM Stack）示例：
 */
public class JvmMemoryDemo {
    // 静态变量存储在方法区（JDK8+为元空间）
    private static int staticVar = 100;

    public static void main(String[] args) {
        // user 引用存储在虚拟机栈中，User对象实例存储在堆中
        User user = new User();

        // 数组对象存储在堆中
        String[] arr = new String[10];
    }
}
