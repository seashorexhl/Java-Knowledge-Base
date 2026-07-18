package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.MonotonicStack;

import java.util.Arrays;
import java.util.Stack;

/**
 * @Author: xhl
 * @Date: 2026-06-27 17:08
 * @Description: 591. 4.26 华为OD机试真题 新系统 - 端口流量统计 Port traffic statistics
 *  返回一个数组 表示多少分钟以后出现比当前更大的流量速率，如果没有出现更大的流量速率，则值为0。
 */
public class portTrafficStatistics {
    static void main() {
        int[] portRates = {730, 740, 750, 710, 690, 720, 760, 730};
        portTrafficStatistics pts = new portTrafficStatistics();
        int[] res = pts.nextGreaterRate(portRates);
        System.out.println("暴力解法:"+Arrays.toString(res));
        System.out.println("单调栈解法:" + Arrays.toString(pts.nextGreaterRate1(portRates)));
    }

    // 暴力解法
    public int[] nextGreaterRate(int[] portRates) {
        int n = portRates.length;
        int[] ratesStat = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (portRates[j] > portRates[i]) {
                    ratesStat[i] = j - i;
                    break; // 找到第一个就跳出内层循环
                }
            }
        }
        return  ratesStat;
    }

    /**
     * 方法一： 单调栈
     *
     */
    /* 逻辑简述*/
    // 1.初始化一个结果数组 ratesStat（默认值全为0）和一个用于存储下标的栈 stack
    // 2.遍历数组 portRates，对于每一个元素，检查它是否比栈顶下标对应的元素大。
    // 3.如果大，说明找到了“下一个更大的流量速率”，弹出栈顶元素，并用当前下标减去弹出的下标，得到相差的分钟数，存入结果数组。
    // 4.遍历结束后，栈中剩余的元素说明后面没有比它更大的流量，结果数组中对应位置保持默认的 0
    public int[] nextGreaterRate1(int[] portRates) {
        int n = portRates.length;
        int[] ratesStat = new int[n];
        Stack<Integer> stack = new Stack<>(); // 存储数组下标

        for (int i = 0; i < n; i++) {
            // 当栈不为空，且当前流量速率大于栈顶下标对应的流量速率时
            while (!stack.isEmpty() && portRates[i] > portRates[stack.peek()]) {
                int idx = stack.pop();
                // 计算相差的分钟数
                ratesStat[idx] = i - idx;
            }
            // 将当前下标入栈
            stack.push(i);
        }
        // 栈中剩余的元素表示之后没有出现更大的流量速率，值保持为 0（数组默认初始值即为 0）
        return ratesStat;
    }

    // 二分查找 + 辅助栈（进阶思路）
    public int[] nextGreaterRate2(int[] portRates) {
        int n = portRates.length;
        int[] ratesStat = new int[n];
        Stack<Integer> stack = new Stack<>(); // 存下标

        for (int i = 0; i < n; i++) {

            // 弹出所有小于等于当前元素的栈顶
            while (!stack.isEmpty() && portRates[stack.peek()] <= portRates[i]) {
                stack.pop();
            }
            // 如果栈不为空，栈顶就是右边第一个比它大的
            if (!stack.isEmpty()) {
                ratesStat[i] = stack.peek() - i;
            }
            // 当前元素入栈
            stack.push(i);
        }
        return ratesStat;
    }

}
