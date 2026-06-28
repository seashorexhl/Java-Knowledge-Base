package com.xhl.C06_Algorithm_Skills.SelfSummary.AdvancedAL.KMP;

/**
 * @Author: xhl
 * @Date: 2026-06-10 23:42
 * @Description: KMP 算法
 */
/**
 * KMP（Knuth-Morris-Pratt）算法是一种高效的字符串匹配算法，其核心思想是利用已匹配的信息
 * 避免重复比较，从而将时间复杂度降低到 O(n+m) 。
 * */
public class KMPAlgorithm {

    /**
     * 构建部分匹配表（Next数组/前缀表）
     * @param pattern 模式串
     * @return next数组
     */
    public static int[] getNext(String pattern) {
        int m = pattern.length();
        int[] next = new int[m];
        next[0] = 0; // 第一个字符无前后缀，最长公共前后缀长度为0

        for (int i = 1, j = 0; i < m; i++) {
            // 失配回退：利用已构建的Next数组，回退到合适位置
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            // 匹配成功：最长公共前后缀长度+1
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            // 填充当前位置的Next值
            next[i] = j;
        }
        return next;
    }

    /**
     * KMP 搜索算法
     * @param text 主串（目标串）
     * @param pattern 模式串
     * @return 匹配成功的起始下标，若未找到返回 -1
     */
    public static int kmpSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        if (m == 0) return 0;

        int[] next = getNext(pattern); // 计算部分匹配表

        for (int i = 0, j = 0; i < n; i++) {
            // 发生不匹配时，根据next数组调整模式串的指针j
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            // 当前字符匹配，继续向后匹配
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            // 整个模式串匹配成功，返回起始位置
            if (j == m) {
                return i - j + 1;
            }
        }
        return -1; // 匹配失败
    }

    public static void main(String[] args) {
        String text = "BBC ABCDAB ABCDABCDABDE";
        String pattern = "ABCDABD";

        int index = kmpSearch(text, pattern);
        System.out.println("主串: " + text);
        System.out.println("模式串: " + pattern);

        if (index != -1) {
            System.out.println("匹配成功，首次出现的位置索引为: " + index);
        } else {
            System.out.println("未找到匹配的子串");
        }
    }
}
