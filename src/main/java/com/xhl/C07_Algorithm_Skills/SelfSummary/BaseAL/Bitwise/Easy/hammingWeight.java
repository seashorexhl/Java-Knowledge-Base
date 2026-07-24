package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.Bitwise.Easy;

/**
 * @Author: xhl
 * @Date: 2026-07-03 10:03
 * @Description: 191. 位1的个数
 */
public class hammingWeight {
    static void main() {
        int n =11;

    }
    /**
     *  方法一：循环检查二进制位
     * */
    public int hammingWeight(int n) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                ret++;
            }
        }
        return ret;
    }
    /**
     *  方法二：位运算优化
     * */
    public int hammingWeight1(int n) {
        int ret = 0;
        while (n != 0) {
            n &= n - 1;
            ret++;
        }
        return ret;
    }

}
