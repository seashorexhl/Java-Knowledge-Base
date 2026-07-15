package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.SlideWindow.middle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: xhl
 * @Date: 2026-06-20 14:16
 * @Description:  📝 实战演示：无重复字符的最长子串 (LeetCode 3)
 */
public class lengthOfLongestSubstring {
    static void main() {
        String s = "abcabcbb";
        lengthOfLongestSubstring ls = new lengthOfLongestSubstring();
        int i = ls.lengthOfLongestSubstring(s);
        System.out.println("无重复字符的最长子串 :" + i);
    }
    // 方法一：滑动窗口
    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0;
        int res = 0;
        // 哈希集合，记录每个字符是否出现过
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
    //  LeetCode 上 滑动窗口 HashSet
    public int lengthOfLongestSubstring1(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

}

