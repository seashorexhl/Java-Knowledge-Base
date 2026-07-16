package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.May;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xhl
 * @Date: 2026-07-10 03:01
 * @Description:    correct essays 小学英语老师批改作文-100分
 * 输入：一个仅包含ASCII字符的本文字符串 story。
 * 输出：请你找出批改后的作文中，最长的不包含重复字符的子串长度。
 */
public class correctEssays {
    static void main(String[] args) {
        String story = "Hello World";
        int function = function(story);
        System.out.println(function);
    }

    // 滑动窗口
    public static int function(String story) {
        // 第一步：边界检查与数据预处理
        if (story == null || story.isEmpty()) return 0;
        String s = story.trim();
        s = s.replaceAll("\\s+","");
        s = s.toLowerCase();
        // 第二步：初始化滑动窗口与辅助数据结构
        Map<Character, Integer> charIndex = new HashMap<>();
        int left = 0;
        int maxLen = 0;
        // 第三步：右指针扩张与窗口动态调整（核心逻辑）
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (charIndex.containsKey(c) && charIndex.get(c) >= left) {
                left = charIndex.get(c) + 1;
            }
            charIndex.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        // 第四步：更新最大长度并返回结果
        return maxLen; //这一段 有多重要
    }
}
