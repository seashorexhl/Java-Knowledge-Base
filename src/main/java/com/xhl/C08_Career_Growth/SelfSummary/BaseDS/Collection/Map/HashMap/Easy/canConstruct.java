package com.xhl.Career_Growth.SelfSummary.BaseDS.Collection.Map.HashMap.Easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xhl
 * @Date: 2026-06-23 21:07
 * @Description: 383. 赎金信
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 * 如果可以，返回 true ；否则返回 false 。
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 */
public class canConstruct {
    static void main() {
        String ransomNote = "aa", magazine = "aaab";
        canConstruct cc = new canConstruct();
        System.out.printf("是否："+cc.canConstruct1(ransomNote, magazine));
    }
    // 使用 HashMap
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        Map<Character,Integer> set = new HashMap<>();
        int n = magazine.length();
        for (int i = 0; i < n; i++) {
            if(set.containsKey(magazine.charAt(i))){
                set.put(magazine.charAt(i),set.get(magazine.charAt(i))+1);
            }else {
                set.put(magazine.charAt(i),1);
            }
        }
        int m = ransomNote.length();
        for (int j = 0; j< m; j++) {
            char c = ransomNote.charAt(j);
            // 【修改2】：不仅判断字符是否存在，还要判断剩余数量是否大于 0
            if (set.containsKey(c) && set.get(c) > 0) {
                set.put(c, set.get(c) - 1);
            } else {
                // 如果不存在，或者数量已经用完（<=0），直接返回 false
                return false;
            }
        }
        return  true;
    }
    // 哈希map
    public boolean canConstruct1(String ransomNote, String magazine) {
        Map<Character,Integer> rMap = new HashMap<>();
        Map<Character,Integer> mMap = new HashMap<>();

        for(char c :ransomNote.toCharArray()){
            rMap.put(c, rMap.getOrDefault(c,0) +1);
        }
        for(char c :magazine.toCharArray()){
            mMap.put(c, mMap.getOrDefault(c, 0) + 1);
        }
        for(char key :rMap.keySet()){
            if(rMap.get(key) > mMap.getOrDefault(key, 0))
                return false;
        }
        return true;

    }
    //方法一：字符统计 数组法
    public boolean canConstruct2(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        int[] cnt = new int[26];
        for (char c : magazine.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            cnt[c - 'a']--;
            if(cnt[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

}
