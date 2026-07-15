package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.April;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-07-15 00:14
 * @Description: 核心代码编程-文档特征提取-100分
 * 找出给定字符串数组中所有字符串的“公共字符”，并按照字典序返回这些公共字符
 * （包含重复字符，重复次数取所有字符串中的最小出现次数
 */
public class extractFeature {
    static void main(String[] args) {
        String[] docs = {"hello","hollow","halloween"};
        String s = extractFeature(docs);
        System.out.println(s);
    }
    /**
     *  方法一： 哈希表 + 字符串处理 +集合的交集运算
     * */
    public static String extractFeature(String[] docs) {
        // 1. 边界条件判断：如果字符串数组为空，直接返回空字符串
        if (docs.length == 0) return "";

        // 2. 找出所有字符串中都包含的【公共字符集合】
        Set<Character> common = new HashSet<>();
        for (char c : docs[0].toCharArray()){
            common.add(c);
        }
        // 遍历剩余的字符串，不断求交集
        for (int i = 1; i < docs.length; i++) {
            // 将当前字符串的字符放入临时集合 set 中（自动去重）
            Set<Character> set = new HashSet<>();
            for (char c : docs[i].toCharArray()) set.add(c);
            // 核心操作：保留 common 和 set 的交集，即只保留两边都有的字符
            common.retainAll(set);
        }
        // 如果求交集后集合为空，说明没有公共字符，直接返回空字符串
        if (common.isEmpty()){
            return "";
        }
        // 3. 统计每个公共字符在所有字符串中的【最小出现次数】
        List<Character> resList = new ArrayList<>();
        // 将公共字符集合转为列表并排序，保证后续结果按字典序排列
        List<Character> sorted = new ArrayList<>(common);
        Collections.sort(sorted);
        // 遍历每一个公共字符
        for (char c : sorted) {
            int minCount = Integer.MAX_VALUE;
            // 遍历所有字符串，统计当前字符 c 在每个字符串中的出现次数
            for (String s : docs) {
                int cnt = 0;
                for (char ch : s.toCharArray())
                    if (ch == c) cnt++;
                minCount = Math.min(minCount, cnt);
            }
            // 取所有字符串中该字符出现次数的【最小值】
            for (int i = 0; i < minCount; i++)
                resList.add(c);
        }
        // 4. 对结果列表再次排序（其实上面已经按字典序遍历了，这里属于双重保险）
        Collections.sort(resList);
        // 5. 将字符列表拼接成最终的字符串并返回
        StringBuilder sb = new StringBuilder();
        for (char c : resList) sb.append(c);
        return sb.toString();
    }
    /**
     *  方法二： int[26] 数组
     *      初始化基准数组 逐个求交集 直接生成结果
     * */
    public String commonChars(String[] docs) {
        // 1. 边界条件判断
        if (docs == null || docs.length == 0) return "";

        // 2. 初始化基准频次数组：记录第一个字符串中各字符的出现次数
        int[] commonCount = new int[26];
        for (char c : docs[0].toCharArray()) {
            commonCount[c - 'a']++;
        }

        // 3. 遍历剩余的字符串，不断与基准数组求“最小频次交集”
        for (int i = 1; i < docs.length; i++) {
            // 统计当前字符串的字符频次
            int[] currentCount = new int[26];
            for (char c : docs[i].toCharArray()) {
                currentCount[c - 'a']++;
            }

            // 核心操作：逐位比较，只保留两者中的较小值（相当于求交集）
            for (int j = 0; j < 26; j++) {
                commonCount[j] = Math.min(commonCount[j], currentCount[j]);
            }
        }

        // 4. 根据基准数组构建最终结果
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            // 如果该字符的公共最小频次大于 0，则按次数追加到结果中
            // 因为 i 是从 0 到 25 递增的，结果天然按字典序排列，无需额外排序！
            while (commonCount[i] > 0) {
                sb.append((char) (i + 'a'));
                commonCount[i]--;
            }
        }

        return sb.toString();
    }
}
