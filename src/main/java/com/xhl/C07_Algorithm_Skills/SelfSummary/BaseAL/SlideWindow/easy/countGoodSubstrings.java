package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.SlideWindow.easy;

/**
 * @Author: xhl
 * @Date: 2026-07-17 20:52
 * @Description: 1876. 长度为三且各字符不同的子字符串
 * 如果一个字符串不含有任何重复字符，我们称这个字符串为 好 字符串。
 * 给你一个字符串 s ，请你返回 s 中长度为 3 的 好子字符串 的数量。
 * 注意，如果相同的好子字符串出现多次，每一次都应该被记入答案之中。
 * 子字符串 是一个字符串中连续的字符序列。
 */
public class countGoodSubstrings {
    static void main(String[] args) {
        String s = "aababcabc";
        countGoodSubstrings cgs = new countGoodSubstrings();

        int goodSubstrings = cgs.countGoodSubstrings(s);
        System.out.println("s 中长度为 3 的 好子字符串 的数量:" + goodSubstrings);
    }

    // 方法一：遍历起始下标
    public int countGoodSubstrings(String s) {
        int n = s.length(); // 字符串 长度
        int res = 0; //计数返回值
        for (int i = 0; i < n - 2; i++) {
            if (s.charAt(i) != s.charAt(i + 1) && s.charAt(i) != s.charAt(i + 2) && s.charAt(i + 1) != s.charAt(i + 2)) {
                res++;
            }
        }
        return res;
    }

    // 方法二： 滑动窗口
    public int countGoodSubstrings1(String s) {
        int n = s.length();
        int res = 0;
        int left = 0;

        if (n < 3) return 0; // 长度不足3直接返回0
        for (int right = 0; right < n; right++) {
            // 当窗口大小达到 3 时进行判断
            if (right - left + 1 == 3) {
                // 检查窗口内的3个字符是否互不相同
                if (s.charAt(left) != s.charAt(left + 1) &&
                        s.charAt(left) != s.charAt(right) &&
                        s.charAt(left + 1) != s.charAt(right)) {
                    res++;
                }
                // 窗口向右滑动，左指针跟进
                left++;
            }
        }
        return res;
    }

    //方法三： 哈希表 + 滑动窗口
    public int countGoodSubstrings2(String s) {
        int n = s.length();
        if (n < 3) return 0;

        int res = 0;
        int[] count = new int[26]; // 记录窗口内字符出现的次数
        int left = 0;

        for (int right = 0; right < n; right++) {
            // 1. 右边界字符入窗
            count[s.charAt(right) - 'a']++;

            // 2. 当窗口大小超过 3 时，左边界字符出窗
            if (right - left + 1 > 3) {
                count[s.charAt(left) - 'a']--;
                left++;
            }

            // 3. 窗口大小刚好为 3 时，检查是否所有字符都只出现 1 次
            if (right - left + 1 == 3) {
                // 因为窗口大小是3，只要3个字符互不相同，每个字符频次必然都是1
                // 这里可以直接判断刚刚加入的字符、以及相邻字符，或者遍历count数组
                // 为了简单，这里用和上面一样的直接比较法，或者检查 count 数组中对应3个字符的频次
                if (count[s.charAt(left) - 'a'] == 1 &&
                        count[s.charAt(left + 1) - 'a'] == 1 &&
                        count[s.charAt(right) - 'a'] == 1) {
                    res++;
                }
            }
        }
        return res;
    }
}
