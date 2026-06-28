package com.xhl.Career_Growth.SelfSummary.BaseDS.Stack.MonotonicStack.middle;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: xhl
 * @Date: 2026-06-27 16:48
 * @Description: Q2. 每日温度
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i]
 * 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 */
public class dailyTemperatures {
    static void main() {
        int[] temperatures = {73,74,75,71,69,72,76,73};
        dailyTemperatures dt = new dailyTemperatures();
        int[] ints = dt.dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(ints));

    }
    // 方法一：暴力
    public int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        int[] ans = new int[length];
        int[] next = new int[101];
        Arrays.fill(next, Integer.MAX_VALUE);
        for (int i = length - 1; i >= 0; --i) {
            int warmerIndex = Integer.MAX_VALUE;
            for (int t = temperatures[i] + 1; t <= 100; ++t) {
                if (next[t] < warmerIndex) {
                    warmerIndex = next[t];
                }
            }
            if (warmerIndex < Integer.MAX_VALUE) {
                ans[i] = warmerIndex - i;
            }
            next[temperatures[i]] = i;
        }
        return ans;

    }
    //  方法二：单调栈
    public int[] dailyTemperatures1(int[] temperatures) {
        int length = temperatures.length;
        int[] ans = new int[length];
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < length; i++) {
            int temperature = temperatures[i];
            while (!stack.isEmpty() && temperature > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }
}
