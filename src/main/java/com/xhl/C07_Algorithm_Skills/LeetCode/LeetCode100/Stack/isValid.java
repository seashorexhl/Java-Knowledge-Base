package com.xhl.C07_Algorithm_Skills.LeetCode.LeetCode100.Stack;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Author: xhl
 * @Date: 2026-04-22 15:24
 * @Description: 有效的括号  大型网站架构实战
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 */
public class isValid {
    public static void main(String[] args) {
        String s = "()[]{}(";
        isValid iv =  new isValid();
        System.out.println(iv.isValid(s));
    }

    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
}
