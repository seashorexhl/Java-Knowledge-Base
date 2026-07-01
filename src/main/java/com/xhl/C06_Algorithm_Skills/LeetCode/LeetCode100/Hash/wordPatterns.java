package com.xhl.C06_Algorithm_Skills.LeetCode.LeetCode100.Hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xhl
 * @Date: 2026-06-09 16:16
 * @Description: 单词规律
 */
public class wordPatterns {

    public static void main(String[] args) {
        String s  =  "dog cat cat dog";
        String pattern = "abba";

        wordPatterns wp = new wordPatterns();
        boolean res = wp.wordPattern(s,pattern);
        System.out.println("是否遵循 相同的规律？："+res);
    }
    //  哈希表 判断是否为 双射关系
    public boolean wordPattern(String pattern, String str) {

        Map<String,Character> map = new HashMap<>();
        Map<Character,String> map1 = new HashMap<>();

        int m = str.length();
        int i = 0;

        for (int p = 0; p < pattern.length(); ++p) {
            char ch = pattern.charAt(p);
            if (i >= m) {
                return false;
            }
            int j = i;
            while (j < m && str.charAt(j) != ' ') {
                j++;
            }
            String tmp = str.substring(i, j);
            if (map.containsKey(tmp) && map.get(tmp) != ch) {
                return false;
            }
            if (map1.containsKey(ch) && !tmp.equals(map1.get(ch))) {
                return false;
            }
            map.put(tmp, ch);
            map1.put(ch, tmp);
            i = j + 1;
        }
        return i >= m;
    }

}
