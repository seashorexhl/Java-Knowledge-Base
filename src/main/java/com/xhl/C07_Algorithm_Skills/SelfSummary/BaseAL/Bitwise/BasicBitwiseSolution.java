package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.Bitwise;

/**
 * @Author: xhl
 * @Date: 2026-06-10 03:01
 * @Description: 位运算 相关模板
 */
public class BasicBitwiseSolution {

    /*1. 基础位运算符速查与实战*/
    public class BasicBitwiseTemplate {
        public static void main(String[] args) {
            int a = 5; // 二进制: 0101
            int b = 3; // 二进制: 0011

            // 1. 按位与 (&): 有0则0，全1才1 (常用于掩码、判断奇偶)
            System.out.println(a & b);   // 输出: 1 (0001)

            // 2. 按位或 (|): 有1则1，全0才0 (常用于设置特定位为1)
            System.out.println(a | b);   // 输出: 7 (0111)

            // 3. 按位异或 (^): 相同为0，相异为1 (常用于切换状态、找唯一数)
            System.out.println(a ^ b);   // 输出: 6 (0110)

            // 4. 按位取反 (~): 0变1，1变0 (正数取反必变负数)
            System.out.println(~a);      // 输出: -6
        }
    }
    /*2.移位运算符模板*/
    public class ShiftOperatorTemplate {
        public static void main(String[] args) {
            int n = 8;

            // 1. 左移 (<<): 低位补0，等价于快速乘以 2^k
            System.out.println(n << 1);  // 输出: 16 (相当于 8 * 2)

            // 2. 算术右移 (>>): 高位补【符号位】，等价于快速除以 2^k
            System.out.println(-8 >> 1); // 输出: -4 (保留负号)

            // 3. 无符号右移 (>>>): Java特有，高位强制补【0】，忽略符号位
            System.out.println(-8 >>> 1);// 输出: 2147483644 (处理无符号数据的利器)
        }
    }
    /*3.高频算法技巧封装类*/
    public class BitTricksUtil {

        // 1. 快速判断奇偶性 (比 % 2 更高效)
        public static boolean isEven(int n) {
            return (n & 1) == 0;
        }

        // 2. 不使用临时变量交换两个数 (利用异或特性: a^a=0, a^0=a)
        public static void swap(int[] arr, int i, int j) {
            if (i != j) { // 防止同一个位置交换导致清零
                arr[i] ^= arr[j];
                arr[j] ^= arr[i];
                arr[i] ^= arr[j];
            }
        }

        // 3. 消除最低位的 1 (常用于统计二进制中1的个数/汉明重量)
        public static int countOnes(int n) {
            int count = 0;
            while (n != 0) {
                n &= (n - 1); // 核心技巧
                count++;
            }
            return count;
        }

        // 4. 获取最低位的 1 (例如用于找出数组中只出现一次的数字的辅助)
        public static int lowestOneBit(int n) {
            return n & -n;
        }

        // 5. 判断是否为 2 的幂 (2的幂的二进制只有一个1)
        public static boolean isPowerOfTwo(int n) {
            return n > 0 && (n & (n - 1)) == 0;
        }

        // 6. 位掩码操作 (权限控制、状态机常用)
        public static void bitmaskDemo() {
            int flags = 0;
            flags |= (1 << 2);              // 设置第2位为1
            boolean hasFlag = (flags & (1 << 2)) != 0; // 检查第2位是否为1
            flags &= ~(1 << 2);             // 清除第2位(置为0)
        }
    }
}
