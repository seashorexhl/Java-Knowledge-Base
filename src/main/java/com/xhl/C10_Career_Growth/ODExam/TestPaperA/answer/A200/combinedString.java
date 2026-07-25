package com.xhl.C10_Career_Growth.ODExam.TestPaperA.answer.A200;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-07-04 20:43
 * @Description: 07 combined string 字母组合过滤组合字符串
 */
public class combinedString {
    static String[] map = {"abc", "def", "ghi", "jkl", "mno", "pqr", "st", "uv", "wx", "yz"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 输入数字字符串
        char[] digits = sc.next().toCharArray();
        // 输入屏蔽字符串
        String filter = sc.next();

        // 根据数字字符串得到每个数字对应的字母字符串
        String[] letters = new String[digits.length];
        for (int i = 0; i < digits.length; i++) {
            letters[i] = map[digits[i] - '0'];
        }

        // 用于存储结果的字符串
        StringBuilder sb = new StringBuilder();
        // 开始进行深度优先搜索
        dfs(letters, 0, new StringBuilder(), sb, filter, new HashSet<>());

        // 输出结果
        System.out.println(sb.toString());
    }
    /**
     *  深度优先遍历
     * */
    public static void dfs(
            String[] letters, int index, StringBuilder path, StringBuilder res, String filter, HashSet<Character> used) {
        if (index == letters.length) {
            // 过滤包含屏蔽字符串的路径
            if (!path.toString().contains(filter)) {
                res.append(path).append(",");
            }
            return;
        }

        // 对于每个数字，遍历其对应的字母字符串
        for (int i = 0; i < letters[index].length(); i++) {
            char c = letters[index].charAt(i);
            if (!used.contains(c)) {
                path.append(c);
                used.add(c);
                dfs(letters, index + 1, path, res, filter, used);
                path.deleteCharAt(path.length() - 1);
                used.remove(c);
            }
        }
    }

}
