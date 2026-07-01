package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseAL.Simulate;

/**
 * @Author: xhl
 * @Date: 2026-06-19 09:36
 * @Description: 模拟 标准模板（需要一定的技巧）
 */
public class SimulateTemplate {
    static void main() {
        int num = 88;
        /*提取各位数字*/
        /**
         * 优化 提升 方法：
         * 先想逻辑，再敲代码：在动手写代码前，先在纸上或脑海里想清楚：我需要几个变量？需不需要数组？用什么循环最简洁？
         * 追求极简：每次写完代码后，问自己一个问题：“我能把代码行数砍掉一半，或者把数组去掉吗？
         * */
        while (num > 0) {
            int digit = num % 10; // 取个位
            num /= 10;            // 去掉个位
        }
    }

}
