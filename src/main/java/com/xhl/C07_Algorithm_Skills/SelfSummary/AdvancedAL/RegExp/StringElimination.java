package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.RegExp;

import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-07-16 17:34
 * @Description: 字符擦串除  200 分
 * 栈 Stack StringBuilder 模拟栈
 * 用 StringBuilder 可以省去最后把 Stack 里的元素一个个 pop 出来再反转的麻烦
 *
 * 给定一个仅包含小写字母的字符串，每次可以消除两个相邻且相同的字符。消除后，剩下的字符会拼接在一起，
 * 如果又产生了相邻且相同的字符，则继续消除，直到无法消除为止。输出最终剩下的字符串。
 * 示例 1：输入 aabbcc，输出 ""（空字符串）
 * 示例 2：输入 abba，输出 "" （先消 bb 变成 aa，再消 aa）
 * 示例 3：输入 abccba，输出 "" （先消 cc 变成 abba，再消 bb，最后消 aa）
 */
public class StringElimination {
    /**
     * 遍历字符串，每次拿当前字符和栈顶元素比对。如果相同，说明可以“消除”，直接把栈顶元素弹出
     *（pop）；如果不相同，就把当前字符压入栈（push）中。最后把栈里剩下的元素拼成字符串即可。
     * */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();

        // 用 StringBuilder 模拟栈，最后可以直接作为结果输出，省去反转操作
        StringBuilder stack = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);

            // 如果栈不为空，且当前字符和栈顶字符相同，则触发“消除”
            if (stack.length() > 0 && stack.charAt(stack.length() - 1) == currentChar) {
                stack.deleteCharAt(stack.length() - 1); // 弹出栈顶元素
            } else {
                stack.append(currentChar); // 否则压入栈中
            }
        }

        System.out.println(stack.toString());
    }

}
