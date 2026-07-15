package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.String.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: xhl
 * @Date: 2026-06-21 08:23
 * @Description: 统计范围内的元音字符串数
 */
public class vowelStrings {
    static void main() {
        String[] words = {"are","amy","u"};
        int left = 0;
        int right = 2;

        vowelStrings vs = new vowelStrings();
        System.out.println(vs.vowelStrings(words, left, right));
    }

    //    方法一：枚举范围内的字符串
    public int vowelStrings(String[] words, int left, int right) {
        int ans = 0;
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');

        for (int i = left; i < right; i++) {
            if(set.contains(words[i].charAt(0)) && set.contains(words[i].charAt(words[i].length()-1))) {}
            {
                ans++;
            }
        }

        return ans;
    }
}
