package com.xhl.C07_Algorithm_Skills.LeetCode.LeetCode100.LinkedList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xhl
 * @Date: 2026-06-09 23:26
 * @Description: 有效的 字母异或位 （字符串 排序后相等）
 *
 */
public class isAnagram {
    public static void main(String[] args) {
        String s = "anagram", t = "nagaram";
        isAnagram iA = new isAnagram();
        System.out.println("是否是字母异或位:"+iA.isAnagram(s, t));
    }
    // 方法一 : 排序
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }

    // 方法二 :哈希表
    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
    // 方法三:用哈希表维护对应字符的频次即可。同时读者需要注意 Unicode 一个字符可能对应多个字节的问题，
    // 不同语言对于字符串读取处理的方式是不同的。
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> table = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            table.put(ch, table.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            table.put(ch, table.getOrDefault(ch, 0) - 1);
            if (table.get(ch) < 0) {
                return false;
            }
        }
        return true;
    }

}
