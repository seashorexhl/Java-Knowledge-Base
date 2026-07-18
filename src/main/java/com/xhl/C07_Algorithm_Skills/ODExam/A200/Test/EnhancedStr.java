package com.xhl.C07_Algorithm_Skills.ODExam.A200.Test;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: xhl
 * @Date: 2026-06-18 01:06
 * @Description: 增强的字符串
 */
public class EnhancedStr {
    static void main() {
        Scanner sc = new Scanner(System.in);
        // 读取源字符串和目标字符串
        String source = sc.nextLine();
        String target = sc.nextLine();

        // 将目标字符串中的可选段标记转换为正则表达式的可选字符
        target = target.replaceAll("\\[(.*?)\\]", "[$1]");

        // 编译目标字符串为正则表达式模式
        Pattern pattern = Pattern.compile(target);
        // 创建匹配器，用于在源字符串中查找匹配的子字符串
        Matcher matcher = pattern.matcher(source);

        // 如果找到匹配的子字符串，则输出匹配的子字符串在源字符串中的起始位置
        if (matcher.find()) {
            System.out.println(matcher.start());
        } else {
            // 如果没有找到匹配的子字符串，则输出-1
            System.out.println(-1);
        }
    }

}
