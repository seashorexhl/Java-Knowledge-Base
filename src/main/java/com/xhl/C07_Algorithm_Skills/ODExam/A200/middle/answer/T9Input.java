package com.xhl.C07_Algorithm_Skills.ODExam.A200.middle.answer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xhl
 * @Date: 2026-06-26 10:42
 * @Description: 05 九宫格按键输入 数字游戏
 *  状态机 + 字符串解析 + 取模运算
 */
public class T9Input {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input_str = reader.readLine();

        // 九宫格枚举信息
        Map<Character, String> char_map = new HashMap<>();
        char_map.put('0', " ");
        char_map.put('1', ",.");
        char_map.put('2', "abc");
        char_map.put('3', "def");
        char_map.put('4', "ghi");
        char_map.put('5', "jkl");
        char_map.put('6', "mno");
        char_map.put('7', "pqrs");
        char_map.put('8', "tuv");
        char_map.put('9', "wxyz");

        StringBuilder res = new StringBuilder();
        // 默认是数字模式
        int mode = 0;

        for (int i = 0; i < input_str.length(); i++) {
            char c = input_str.charAt(i);
            if (Character.isDigit(c)) { // 如果是数字
                if (mode == 0) { // 如果是数字模式，直接加入结果
                    res.append(c);
                } else if (mode == 1) { // 如果是字母模式
                    int j = i;
                    String tempstr = char_map.get(c);
                    while (j < input_str.length() && input_str.charAt(j) == c) { // 统计连续出现的数字个数
                        j++;
                    }
                    int index = (j - i - 1) % tempstr.length(); // 计算对应的字母下标
                    res.append(tempstr.charAt(index)); // 加入结果
                    i = j - 1; // 跳过已经处理的数字
                }
            } else if (c == '#') { // 如果是切换模式符号
                mode = (mode + 1) % 2; // 切换模式
            } else if (c == '/') { // 如果是延迟符号，不做处理
                // 延迟，不做处理
            } else { // 如果是其他字符，直接退出循环
                break;
            }
        }
        System.out.println(res.toString()); // 输出结果
    }
}
