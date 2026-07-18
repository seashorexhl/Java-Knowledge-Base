package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.MonotonicStack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @Author: xhl
 * @Date: 2026-07-18 17:42
 * @Description: Monotonic Stack 单调栈 模板
 */
public class MonotonicStack {
    static void main(String[] args) {

    }
    /**
     *  单调栈 模板
     * */
    public static int[] monotonicStack(int[] arr) {
        int n = arr.length;
        int[] res = new int[n]; // 记录结果

        Arrays.fill(res, -1);   // 默认找不到，填 -1

        // 栈中存放的是数组的【下标】（强烈推荐存下标，方便计算距离和取值）
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // 核心逻辑：当栈不为空，且当前元素 arr[i] 大于栈顶元素时
            // 说明 arr[i] 就是栈顶元素“右侧第一个比它大的元素”
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                int index = stack.pop(); // 弹出栈顶
                res[index] = arr[i];     // 记录结果
            }
            // 将当前元素的下标压入栈中
            stack.push(i);
        }
        return res;
    }

}
