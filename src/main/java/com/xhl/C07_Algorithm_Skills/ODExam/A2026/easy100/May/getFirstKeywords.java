package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.May;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-07-10 01:31
 * @Description: 匹配命令行前缀的关键字-100分
 *  字符串的 前缀匹配 + 状态边界处理以及集合的排序去重
 */

public class getFirstKeywords {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 3. 使用正则 \\s*,\\s* 分割，自动去除逗号前后的空格
        String[] line = sc.nextLine().trim().split(",");
        List<String> commands = Arrays.asList(line);

        String prefix = sc.nextLine().trim();
        List<String> result = getFirstKeywords(commands, prefix);
        for (String s : result) {
            System.out.println(s);
        }
    }
    //
    public static List<String> getFirstKeywords(List<String> commands, String prefix) {
        Set<String> keywords = new HashSet<>();

        for (String cmd : commands) {
            // 确保命令是以 prefix 开头的
            if (cmd.startsWith(prefix)) {
                // 截取 prefix 后面的部分，并去除首尾空格
                String remaining = cmd.substring(prefix.length()).trim();

                // 如果后面还有内容（即不仅仅是 prefix 本身）
                if (!remaining.isEmpty()) {
                    // 按空白字符分割，取第一个单词作为关键字
                    String keyword = remaining.split("\\s+")[0];
                    keywords.add(keyword);
                }
            }
        }

        // 转为 List 并字典序排序
        List<String> sortedKeywords = new ArrayList<>(keywords);
        Collections.sort(sortedKeywords);
        return sortedKeywords;
    }
    public static  List<String> getFirstKeywords1(List<String> commands, String prefix) {
        Set<String> keywords = new HashSet<>();
        for (String cmd : commands) {
            cmd = cmd.trim();
            if (cmd.startsWith(prefix)) {
                int startIndex = prefix.length();
                while (startIndex < cmd.length() && cmd.charAt(startIndex) == ' ') {
                    startIndex++;
                }
                if (startIndex < cmd.length()) {
                    int endIndex = startIndex;
                    while (endIndex < cmd.length() && cmd.charAt(endIndex) != ' ') {
                        endIndex++;
                    }
                    String keyword = cmd.substring(startIndex, endIndex);
                    keywords.add(keyword);
                }
            }
        }
        List<String> sortedKeywords = new ArrayList<>(keywords);
        Collections.sort(sortedKeywords);
        return sortedKeywords;


    }
}
