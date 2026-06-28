package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseAL.SlideWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xhl
 * @Date: 2026-06-20 14:16
 * @Description: 📝 实战演示：无重复字符的最长子串 (LeetCode 3)
 */
public class lengthOfLongestSubstring {
    static void main() {

    }
    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0;
        int res = 0;
        Map<Character, Integer> window = new HashMap<>();

        while (right < s.length()) {
            char c = s.charAt(right);
            window.put(c, window.getOrDefault(c, 0) + 1);
            right++;

            // 当窗口内出现重复字符时，收缩左边界
            while (window.get(c) > 1) {
                char leftChar = s.charAt(left);
                window.put(leftChar, window.get(leftChar) - 1);
                left++;
            }

            // 此时窗口内无重复字符，更新最大长度
            res = Math.max(res, right - left);
        }
        return res;
    }
}

