package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Stack.MonotonicStack.easy;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @Author: xhl
 * @Date: 2026-06-27 16:43
 * @Description: Q1. 商品折扣后的最终价格
 */
public class finalPrices {
    static void main() {
        int[] prices = {8,4,6,2,3};
        finalPrices fp = new finalPrices();
        int[] ints = fp.finalPrices(prices);
        System.out.println("方法一：直接遍历"+Arrays.toString(ints));
        System.out.println("方法二：单调栈" + Arrays.toString(fp.finalPrices1(prices)));

    }
    // 方法一：直接遍历
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int discount = 0;
            for (int j = i + 1; j < n; ++j) {
                if(prices[j] <= prices[i]){
                    discount = prices[j];
                    break;
                }
            }
            ans[i] = prices[i] - discount;
        }
        return ans;
    }
    // 方法二：单调栈
    public int[] finalPrices1(int[] prices) {

        int n = prices.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() > prices[i]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? prices[i] : prices[i] - stack.peek();
            stack.push(prices[i]);
        }
        return ans;

    }
}
