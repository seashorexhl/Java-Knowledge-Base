package com.xhl.C07_Algorithm_Skills.LeetCode.LeetCode100.Hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xhl
 * @Date: 2026-06-09 16:16
 * @Description: 单词规律
 *  哈希表
 */
public class wordPatterns {

    public static void main(String[] args) {
        String s  =  "dog cat cat dog";
        String pattern = "abba";

        wordPatterns wp = new wordPatterns();
        boolean res = wp.wordPattern(s,pattern);
        System.out.println("是否遵循 相同的规律？："+res);
    }
    /**
     *  哈希表 判断是否为 双射关系
     * */
    public boolean wordPattern(String pattern, String str) {
        // map: 记录 "单词 -> 字符" 的映射关系，防止同一个单词对应不同的字符

        Map<String,Character> map = new HashMap<>();
        // map1: 记录 "字符 -> 单词" 的映射关系，防止同一个字符对应不同的单词

        Map<Character,String> map1 = new HashMap<>();

        int m = str.length(); // 字符串 str 的总长度
        int i = 0;// 指针 i，用于在 str 中定位当前正在处理的单词的起始位置
        // 遍历 pattern 中的每一个字符

        for (int p = 0; p < pattern.length(); ++p) {
            char ch = pattern.charAt(p); // 获取当前 pattern 字符
            // 如果 pattern 还没遍历完，但 str 已经到头了，说明 str 的单词数量少于 pattern 字符数量

            if (i >= m) {
                return false;
            }
            // 1. 手动分词：寻找当前单词的结束位置（遇到空格或字符串结尾停止）

            int j = i;
            while (j < m && str.charAt(j) != ' ') {
                j++;
            }
            // 截取当前单词（不包含空格）

            String tmp = str.substring(i, j);
            // 2. 检查 "单词 -> 字符" 映射是否冲突
            // 如果该单词已经存在映射，且映射的字符与当前字符 ch 不一致，则返回 false

            if (map.containsKey(tmp) && map.get(tmp) != ch) {
                return false;
            }
            // 3. 检查 "字符 -> 单词" 映射是否冲突
            // 如果该字符已经存在映射，且映射的单词与当前单词 tmp 不一致，则返回 false

            if (map1.containsKey(ch) && !tmp.equals(map1.get(ch))) {
                return false;
            }
            // 4. 建立/更新双向映射

            map.put(tmp, ch);
            map1.put(ch, tmp);
            // 5. 移动指针 i 到下一个单词的起始位置（跳过当前的空格）

            i = j + 1;
        }
        // 循环结束后，检查 str 是否也刚好遍历完
        // 如果 i < m，说明 str 还有剩余的单词没被 pattern 匹配到（str 单词数 > pattern 字符数）

        return i >= m;
    }

}
