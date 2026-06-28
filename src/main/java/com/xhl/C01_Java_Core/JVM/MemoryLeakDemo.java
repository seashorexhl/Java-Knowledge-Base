package com.xhl.C01_Java_Core.JVM;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-24 20:51
 * @Description: 模拟内存泄漏
 */
public class MemoryLeakDemo {
    private static final List<byte[]> list = new ArrayList<>();
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            byte[] data = new byte[1024 * 1024]; // 每次分配1MB
            list.add(data); // 关键问题：只加不删，导致对象无法被GC回收
        }
    }
}