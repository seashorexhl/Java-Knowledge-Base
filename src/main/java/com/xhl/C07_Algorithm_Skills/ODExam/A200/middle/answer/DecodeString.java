package com.xhl.C09_Career_Growth.ODExam.A200.middle.answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * @Author: xhl
 * @Date: 2026-06-26 10:38
 * @Description: 04 解压报文压缩报文还原
 *  栈 + 字符串解析
 */
public class DecodeString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String compressed_string = scanner.next();

        Stack<List<String>> stack = new Stack<>();
        stack.push(new ArrayList<>(List.of("", "1", ""))); // 使用栈来存储解压后的字符串和重复次数

        String current_str = ""; // 当前字符
        String current_num = ""; // 当前重复次数

        for (char c : compressed_string.toCharArray()) {
            if (Character.isLetter(c)) { // 如果是字母
                current_str += c;
            } else if (Character.isDigit(c)) { // 如果是数字
                current_num += c;
            } else if (c == '[') { // 如果是左括号
                stack.push(new ArrayList<>(List.of(current_str, current_num, ""))); // 将当前字符和重复次数入栈
                current_str = current_num = ""; // 重置当前字符和重复次数
            } else { // 如果是右括号
                List<String> prev = stack.pop();
                String prev_str = prev.get(0);
                int times = Integer.parseInt(prev.get(1));
                String prev_result = prev.get(2);

                String repeated_str = "";
                for (int i = 0; i < times; i++) {
                    repeated_str += prev_result + current_str;
                }

                stack.peek().set(2, stack.peek().get(2) + prev_str + repeated_str); // 更新栈顶元素的结果
                current_str = ""; // 重置当前字符
            }
        }

        String result = stack.peek().get(2) + current_str; // 返回最终的结果
        System.out.println(result);
    }
}
