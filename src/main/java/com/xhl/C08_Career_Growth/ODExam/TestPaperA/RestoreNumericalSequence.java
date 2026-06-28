package com.xhl.Career_Growth.ODExam.TestPaperA;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-06 01:11
 * @Description: 01 RestoreNumericalSequence 恢复数字序列
 * 题目的核心是通过排列组合字符找到能组成连续正整数序列的方案，并返回其中最小的数字。 统计打乱字符的字符串中各字符的数量。
 * 然后遍历所有可能的连续整数序列，对于每个序列，检查序列中的字符数量是否与打乱字符的字符串中各字符数量一致。
 * 如果找到一个匹配的序列，输出序列中最小的数字并结束循环。
 */
public class RestoreNumericalSequence {
    public static void main(String[] args) {
        // 创建一个Scanner对象，用于读取输入
        Scanner sc = new Scanner(System.in);

        // 读取输入的打乱字符的字符串
        String s = sc.next();
        // 读取输入的正整数序列的长度
        int k = sc.nextInt();

        // 创建一个HashMap，用于统计打乱字符的字符串中各字符的数量
        HashMap<Character, Integer> base = new HashMap<>();
        // 遍历打乱字符的字符串
        for (int i = 0; i < s.length(); i++) {
            // 获取字符串中的字符
            char c = s.charAt(i);
            // 将字符及其数量存入HashMap
            base.put(c, base.getOrDefault(c, 0) + 1);
        }

        // 初始化滑动窗口的起始位置
        int i = 1;
        // 当滑动窗口的起始位置小于等于1000减去序列长度加1时，继续循环
        while (i <= 1000 - k + 1) {
            // 创建一个HashMap，用于计算滑动窗口内各字符的数量
            HashMap<Character, Integer> count = new HashMap<>();
            // 遍历滑动窗口内的正整数
            for (int j = i; j < i + k; j++) {
                // 将正整数转换为字符串
                String num = String.valueOf(j);
                // 遍历正整数字符串中的字符
                for (int m = 0; m < num.length(); m++) {
                    // 获取正整数字符串中的字符
                    char c = num.charAt(m);
                    // 将字符及其数量存入HashMap
                    count.put(c, count.getOrDefault(c, 0) + 1);
                }
            }

            // 初始化一个布尔变量，用于判断滑动窗口内各字符数量是否与打乱字符的字符串中各字符数量一致
            boolean isMatch = true;
            // 遍历打乱字符的字符串中的字符
            for (Character c : base.keySet()) {
                // 如果滑动窗口内的字符数量与打乱字符的字符串中的字符数量不一致，将isMatch设为false并跳出循环
                if (!count.containsKey(c) || count.get(c) - base.get(c) != 0) {
                    isMatch = false;
                    break;
                }
            }

            // 如果滑动窗口内各字符数量与打乱字符的字符串中各字符数量一致，则输出滑动窗口的起始位置并返回
            if (isMatch) {
                System.out.println(i);
                return;
            }

            // 更新滑动窗口的起始位置
            i++;
        }
    }

}
