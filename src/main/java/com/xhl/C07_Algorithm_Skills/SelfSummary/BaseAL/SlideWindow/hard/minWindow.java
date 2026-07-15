package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.SlideWindow.hard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: xhl
 * @Date: 2026-06-20 14:53
 * @Description: 📝 实战演示：最小覆盖子串 (LeetCode 76)
 */
public class minWindow {
    // 方法一：滑动窗口
    Map<Character, Integer> ori = new HashMap<>();
    Map<Character, Integer> cnt = new HashMap<>();

    static void main() {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        minWindow ob = new minWindow();
        String s1 = ob.minWindow(s, t);
        System.out.println("最小覆盖子串 :" +s1);
    }

    // 方法一：滑动窗口 使用滑动窗口（双指针）+ 哈希表
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0; // 满足 need 条件的字符种类数
        int start = 0, minLen = Integer.MAX_VALUE;

        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) valid++;
            }

            // 当所有字符都满足条件时，尝试收缩左边界
            while (valid == need.size()) {
                if (right - left < minLen) {
                    start = left;
                    minLen = right - left;
                }
                char leftChar = s.charAt(left);
                left++;
                if (need.containsKey(leftChar)) {
                    if (window.get(leftChar).equals(need.get(leftChar))) valid--;
                    window.put(leftChar, window.get(leftChar) - 1);
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    public String minWindow1(String s, String t) {
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            ++r;
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            while (check() && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }
    //
    public boolean check() {
        Iterator iter = ori.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

}
