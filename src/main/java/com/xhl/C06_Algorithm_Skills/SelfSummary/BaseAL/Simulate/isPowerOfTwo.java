package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseAL.Simulate;

/**
 * @Author: xhl
 * @Date: 2026-06-20 10:25
 * @Description: 是否是 2 的 幂
 */
public class isPowerOfTwo {
    /**
     *  方法二：判断是否为最大 2 的幂的约数
     * */
    static final int BIG = 1 << 30;
    //    方法一：二进制表示

    static void main() {
        int n = 8;
        isPowerOfTwo ipot = new isPowerOfTwo();
        boolean is = ipot.isPowerOfTwo(n);
        System.out.print("是否是 2d的 幂？:");
        System.out.println(is);
    }

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public boolean isPowerOfTwo1(int n) {
        return n > 0 && (n & -n) == n;
    }

    public boolean isPowerOfTwo2(int n) {
        return n > 0 && BIG % n == 0;
    }

}
