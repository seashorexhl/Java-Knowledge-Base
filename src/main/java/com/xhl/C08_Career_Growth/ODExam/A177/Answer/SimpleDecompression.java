package com.xhl.Career_Growth.ODExam.A177.Answer;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: xhl
 * @Date: 2026-04-11 23:20
 * @Description: 03 SimpleDecompression 简易压缩算法
 */
public class SimpleDecompression {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取输入字符串
        String s = scanner.nextLine();

        // 定义匹配非法字符的正则表达式（非数字和小写字母的字符）
        String pat = "[^0-9a-z]";

        // 用于存储数字部分的字符串
        String num = "";

        // 用于存储最终解压缩的结果
        String res = "";

        // 编译正则表达式
        Pattern pattern = Pattern.compile(pat);
        Matcher matcher = pattern.matcher(s);

        // 如果找到非法字符，则直接输出 "!error"
        if (matcher.find()) {
            res = "!error";
        } else {
            // 遍历输入字符串的每一个字符
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                // 如果当前字符是数字，则将其追加到 num 中
                if (Character.isDigit(c)) {
                    num += c;
                }
                // 如果 num 不为空，表示之前有数字，需要进行解压操作
                else if (!num.equals("")) {
                    // 判断数字是否小于等于2，如果是则输入不合法
                    if (Integer.parseInt(num) <= 2) {
                        res = "!error";
                        break;
                    } else {
                        // 将对应数量的字母添加到结果中
                        for (int j = 0; j < Integer.parseInt(num); j++) {
                            res += c;
                        }
                        // 重置 num 为空
                        num = "";
                    }
                }
                // 如果当前字符是字母，且前面没有数字，则直接添加到结果中
                else {
                    res += c;
                }
            }
        }
        // 输出最终结果
        System.out.println(res);
    }

}
