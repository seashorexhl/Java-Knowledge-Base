package com.xhl.Career_Growth.SelfSummary.BaseAL.Recursion;

/**
 * @Author: xhl
 * @Date: 2026-06-10 01:14
 * @Description: 通用数字递归（以阶乘为例）
 */
public class RecursionTemplate {

    // 测试入口
    public static void main(String[] args) {
        RecursionTemplate solution = new RecursionTemplate();
        System.out.println("5的阶乘是: " + solution.factorial(5)); // 输出 120
    }

    /**
     * 计算 n 的阶乘 (n!)
     * @param n 输入的数字
     * @return 计算结果
     */
    public int factorial(int n) {
        // 1. 终止条件 (Base Case)
        // 当 n 为 0 或 1 时，不再递归，直接返回 1
        if (n <= 1) {
            return 1;
        }

        // 2. 递归步骤 (Recursive Step)
        // 将大问题拆解：n! = n * (n-1)!
        // 这里的 n-1 就是向终止条件靠近的操作
        int subResult = factorial(n - 1);

        // 3. 结果处理与返回
        return n * subResult;
    }
}