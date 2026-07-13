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
    // 环形缓冲区（Ring Buffer）中，根据给定的对齐要求，计算下一个写入位置的起始地址，并判断是否有足够的连续空间容纳 pktSize 大小的数据包。
    public static int calculateWriteIndex(int capacity, int align, int readIndex, int writeIndex, int pktSize) {
        int start = (writeIndex + align - 1) & ~(align - 1);
        if (start >= capacity) {
            start = 0;
        }

        int end = start + pktSize;

        if (writeIndex >= readIndex) {
            if (start >= writeIndex) {
                if (end <= capacity) {
                    // 空间足够
                } else {
                    if ((start + pktSize) % capacity <= readIndex) {
                        // 空间足够
                    } else {
                        return -1;
                    }
                }
            } else {
                if (end <= readIndex) {
                    // 空间足够
                } else {
                    return -1;
                }
            }
        } else {
            if (start >= writeIndex) {
                if (end <= readIndex) {
                    // 空间足够
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }

        return end % capacity;
    }
    //    优化 增强可读性
    public static int calculateWriteIndex1(int capacity, int align, int readIndex, int writeIndex, int pktSize) {
        // 1. 计算对齐后的起始写入位置
        // 注意：这里假设 align 是 2 的幂，使用位运算加速。如果不是，需改用 Math.ceilDiv 或常规取模
        int start = (writeIndex + align - 1) & ~(align - 1);

        // 2. 如果对齐后的起始位置超出容量，回绕到头部
        if (start >= capacity) {
            start = 0;
        }

        int end = start + pktSize;
        int availableTail = capacity - start; // 尾部剩余可用空间

        boolean hasSpace;

        // 3. 分两种情况判断空间是否足够
        if (readIndex <= writeIndex) {
            // 情况A：读指针在写指针后面（或重合），缓冲区未回绕
            // 尾部放不下时，尝试从头部开始写（前提是头部空间足够）
            if (end <= capacity) {
                hasSpace = true; // 尾部空间充足
            } else {
                // 尾部不够，检查回绕到头部后空间是否充足
                // 注意：原代码中 (start + pktSize) % capacity 其实就是 end - capacity (当 end > capacity 时)
                hasSpace = (end - capacity) <= readIndex;
            }
        } else {
            // 情况B：读指针在写指针前面，缓冲区已回绕
            // 此时空闲区被数据分割，写指针只能使用 [writeIndex, readIndex) 这段连续空间
            hasSpace = end <= readIndex;
        }

        // 4. 空间不足直接返回 -1
        if (!hasSpace) {
            return -1;
        }

        // 5. 返回对齐后的结束位置（自动处理回绕）
        return end % capacity;
    }
}
