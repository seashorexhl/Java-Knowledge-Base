package com.xhl.C07_Algorithm_Skills.LeetCode.Beginner;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-05 04:11
 * @Description: 最长连续子串
 */
public class LCS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            LCS lcs = new LCS();
            System.out.println(lcs.LCSubstring(str));
        }
        sc.close();
    }

    public int LCSubstring(String str) {
        HashSet<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        int maxLen = 0;
        int curLen = 0;
        for (int i = 0; i < str.length(); i++) {
            if (set.contains(str.charAt(i))) {
                curLen++; // 是元音，当前长度 +1
                maxLen = Math.max(maxLen, curLen); // 顺便更新一下最大值
            }else {
                curLen = 0;
            }
        }
        return maxLen;
    }
}
