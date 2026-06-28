package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseAL.TwoPointers;

/**
 * @Author: xhl
 * @Date: 2026-06-15 02:50
 * @Description: 3. 滑动窗口（可变大小的双指针）
 *
 */
import java.util.HashMap;
import java.util.Map;

/**
 *  应用场景：
 *  最小覆盖子串、最长无重复子串、数组最大和子数组等
 * */
public class SlidingWindow {
    static void main() {

    }
    // 最小覆盖子串（LeetCode 76）
    public String minWindow(String s, String t) {
        // 初始化目标字符计数
        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray())
            need.put(c, need.getOrDefault(c, 0) + 1);

        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int valid = 0; // 当前窗口中满足 need 条件的字符个数

        // 记录最小覆盖子串的起始索引及长度
        int start = 0, len = Integer.MAX_VALUE;

        while (right < s.length()) {
            // 右移窗口
            char c = s.charAt(right);
            right++;

            // 更新窗口数据
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c)))
                    valid++;
            }

            // 判断左侧窗口是否要收缩
            while (valid == need.size()) {
                // 更新最小覆盖子串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }

                // 左移窗口
                char d = s.charAt(left);
                left++;

                // 更新窗口数据
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d)))
                        valid--;
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    // 最长无重复子串（LeetCode 3）
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int maxLen = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            right++;

            // 更新窗口
            window.put(c, window.getOrDefault(c, 0) + 1);

            // 收缩左边界直到无重复
            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                window.put(d, window.get(d) - 1);
            }

            maxLen = Math.max(maxLen, right - left);
        }
        return maxLen;
    }
}