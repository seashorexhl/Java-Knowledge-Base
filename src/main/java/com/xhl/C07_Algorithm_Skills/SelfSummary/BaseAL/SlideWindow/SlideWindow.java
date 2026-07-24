package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.SlideWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xhl
 * @Date: 2026-07-18 17:22
 * @Description: 滑动窗口模板
 * 定长窗口：右进左出，if 判断，一步一缩，求定长极值。
 * 变长窗口：右扩左缩，while 循环，持续收缩，求最长/最短。
 */
public class SlideWindow {
    static void main(String[] args) {

    }

    /**
     * 定长滑动窗口
     * */
    public static int fixedSlideWindow1(int[] arr, int k) { // 建议把 k 作为参数传入

        int n = arr.length;
        if (n < k || k <= 0) return 0; // 防御性编程

        int res = Integer.MIN_VALUE; // 防止全负数数组导致结果错误
        int sum = 0;
        int left = 0;

        for (int right = 0; right < n; right++) { // 用 for 循环控制 right 更直观
            sum += arr[right]; // 1. 扩大窗口

            if (right - left + 1 == k) { // 2. 窗口大小达到 k
                res = Math.max(res, sum); // 更新结果
                sum -= arr[left]; // 3. 收缩窗口
                left++;
            }
        }
        return res;
    }
    /**
     *  变长滑动窗口
     * 1. 扩大窗口：处理 arr[right]
     * 2. 收缩窗口：while (窗口不满足条件) { 处理 arr[left]; left++; }
     * 3. 更新结果：res = Math.max/min(res, 当前窗口大小或sum);
     * */
    public static int variableSlideWindow(String s) {
        int n = s.length();
        int res = 0;
        int left = 0;
        // 使用哈希表记录窗口内字符出现的次数
        Map<Character, Integer> windowMap = new HashMap<>();

        for (int right = 0; right < n; right++) {
            // 1. 扩大窗口：将右边界字符加入窗口
            char rightChar = s.charAt(right);
            windowMap.put(rightChar, windowMap.getOrDefault(rightChar, 0) + 1);

            // 2. 判断是否需要收缩窗口：当窗口内出现重复字符时（即不满足“无重复”条件）
            // 核心区别：这里是一个 while 循环，会一直收缩直到满足条件为止！
            while (windowMap.get(rightChar) > 1) {
                char leftChar = s.charAt(left);
                windowMap.put(leftChar, windowMap.get(leftChar) - 1);
                left++; // 左指针右移，缩小窗口
            }

            // 3. 更新结果：此时窗口 [left, right] 一定是满足条件的最长子串
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}