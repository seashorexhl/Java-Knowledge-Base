package com.xhl.C06_Algorithm_Skills.ODExam.A2026.easy100.May;

/**
 * @Author: xhl
 * @Date: 2026-07-10 02:35
 * @Description: 循环内存存取计算-100分
 * 计算把数据包写入环形缓冲区后，write_index的位置。如果在写入新的数据时，环形缓冲区放不下，则返回-1。
 */
public class calculateWriteIndex {
    static void main() {
        int capacity  = 100; // 内存的空间大小
        int align = 4; // 字节对齐的值
        int readIndex = 0; // 当前读索引的值
        int writeIndex = 1; // 当前写索引的值
        int pktSize = 10; // 写入的数据包的长度
        int i = calculateWriteIndex(capacity, align, readIndex, writeIndex, pktSize);
        System.out.println(i);
    }

    public static int calculateWriteIndex(int capacity, int align, int readIndex, int writeIndex, int pktSize) {


        return -1;
    }
}
