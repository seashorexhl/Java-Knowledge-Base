package com.xhl.C01_Java_Core.java17.JVM;

/**
 * @Author: xhl
 * @Date: 2026-06-24 20:48
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = a + b;
        System.out.println("线上排查常用命令：\n" +
                "查看 JVM 进程 ID：jps -l\n" +
                "查看堆内存使用情况：jmap -heap <pid>\n" +
                "手动导出 Dump 文件：jmap -dump:format=b,file=dump.hprof <pid>\n" +
                "分析线程阻塞/死锁：jstack <pid>");
    }
}