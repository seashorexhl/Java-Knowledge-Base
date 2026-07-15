package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.Simulate;

/**
 * @Author: xhl
 * @Date: 2026-06-20 11:11
 * @Description: 是否是 3的 幂
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
 * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x
 */
public class isPowerOfThree {
    static void main() {
        int n = 9;
        isPowerOfThree isPowerOfThree = new isPowerOfThree();
        System.out.println(isPowerOfThree.isPowerOfThree(n));
    }
    //    方法一：试除法
    public boolean isPowerOfThree(int n) {
        while (n != 0 && n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }
    /**
     * 方法二：判断是否为最大 3 的幂的约数
     */
    public boolean isPowerOfThree1(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

}
