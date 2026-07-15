package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Array.hard;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: xhl
 * @Date: 2026-07-03 18:14
 * @Description: 42. 接雨水 ⭐⭐⭐⭐⭐
 */
public class trap {
    static void main() {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int trap = trap(height);
        System.out.println("下雨之后能接多少雨水:" + trap);
    }

    /**
     * 方法一：动态规划
     * */
    // 对于每一个位置，它能接的水量 = min(左侧最高柱子, 右侧最高柱子) - 当前柱子高度
    public static int trap(int[] height) {
        int n = height.length;
        // 边界处理：如果没有柱子，直接返回 0
        if (n == 0) {
            return 0;
        }
        // 1. 预处理：从左向右遍历，计算每个位置左侧（包含自身）的最大高度
        int[] leftMax = new int[n];
        leftMax[0] = height[0]; // 第一个柱子的左侧最大值就是它自己
        for (int i = 1; i < n; ++i) {
            // 当前位置的左侧最大值 = 前一个位置的左侧最大值 与 当前高度 的较大者
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        // 2. 预处理：从右向左遍历，计算每个位置右侧（包含自身）的最大高度
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];// 最后一个柱子的右侧最大值就是它自己
        for (int i = n - 2; i >= 0; --i) {
            // 当前位置的右侧最大值 = 后一个位置的右侧最大值 与 当前高度 的较大者
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        // 3. 计算总水量：遍历每个位置，累加该位置能接的水量
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            // 当前水量 = 左右两侧最大高度的较小值（木桶效应） - 当前柱子的高度
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }

    /**
     *  方法二：单调栈
     * */
    public int trap1(int[] height) {
        int ans = 0;
        // 使用双端队列作为单调栈，里面存储的是柱子的【索引】
        // 栈内元素对应的高度始终保持【单调递减】（栈底最高，栈顶最低）
        Deque<Integer> stack = new LinkedList<>();

        int n = height.length;
        // 遍历每一根柱子
        for (int i = 0; i < n; ++i) {
            // 【核心逻辑】：当栈不为空，且当前柱子高度 > 栈顶柱子高度时
            // 说明遇到了一个“右挡板”，与栈顶元素（底部）和新的栈顶元素（左挡板）形成了一个凹槽
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                // 1. 弹出栈顶元素，这个元素就是当前凹槽的【底部】
                int top = stack.pop();
                // 2. 如果弹出底部后栈空了，说明左边没有“左挡板”了，无法形成凹槽，直接跳出循环
                if (stack.isEmpty()) {
                    break;
                }
                // 3. 获取新的栈顶元素，这就是当前凹槽的【左挡板】索引
                int left = stack.peek();
                // 4. 计算当前凹槽的【宽度】 = 右挡板索引(i) - 左挡板索引(left) - 1
                int currWidth = i - left - 1;
                // 5. 计算当前凹槽的【高度】 = 左右挡板中较矮的那个 - 底部的高度
                int currHeight = Math.min(height[left], height[i]) - height[top];
                // 6. 累加这一层横向的水量 (面积 = 宽 × 高)
                ans += currWidth * currHeight;
            }
            // 将当前柱子的索引压入栈中，继续维持栈的单调递减特性
            stack.push(i);
        }
        return ans;
    }
    /**
     *  方法三：双指针
     * */
    public int trap2(int[] height) {
        int ans = 0;
        // 1. 初始化左右指针，分别指向数组的最左端和最右端

        int left = 0, right = height.length - 1;
        // 2. 初始化左右两侧目前遇到过的最高墙的高度

        int leftMax = 0, rightMax = 0;
        // 3. 核心循环：当左右指针未相遇时，不断向内收缩

        while (left < right) {
            // 4. 动态更新：无论哪边是短板，都先把各自当前的最高墙高度更新好

            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            // 5. 辩证逻辑判断：寻找当前的“短板”
            if (height[left] < height[right]) {
                // 6. 左边是短板：说明左边最高墙 <= 右边最高墙
                ans += leftMax - height[left];
                ++left;
            } else {
                // 7. 右边是短板（或两边一样高）：说明右边最高墙 <= 左边最高墙
                ans += rightMax - height[right];
                --right;
            }
        }
        return ans;
    }

}
