package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @Author: xhl
 * @Date: 2026-06-14 01:50
 * @Description: 逆波兰表达式求值
 *  逆波兰表达式也称后缀表达式
 */
/**
 *  解题思路 ：使用栈来 存储 操作符 和 操作数
 * */
class Solution {
    public static void main(String[] args) {
        String[] tokens = {"2","1","+","3","*"};
        Solution s = new Solution();

        System.out.println(s.evalRPN0(tokens));
    }
    /**
     * 方法一：
     * */
    public int evalRPN0(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int left,right;

        for(String token:tokens){
            switch(token){
                case"+":
                    stack.push(stack.pop()+stack.pop());
                    break;
                case"-":
                    right = stack.pop();
                    left = stack.pop();
                    stack.push(left - right);
                    break;
                case"*":
                    stack.push(stack.pop()*stack.pop());
                    break;
                case"/":
                    right = stack.pop();
                    left = stack.pop();
                    stack.push(left/right);
                    break;
                default:
                    stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }
    /**
     * 方法一：栈
     */
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = tokens.length;
        for (int i = 0; i < n; i++) {
            String token = tokens[i];
            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2);
                        break;
                    default:
                }
            }
        }
        return stack.pop();
    }

    public boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }

    /**
     * 方法二：数组模拟栈
     * */
    public int evalRPN1(String[] tokens) {
        int n = tokens.length;
        int[] stack = new int[(n + 1) / 2];
        int index = -1;
        for (int i = 0; i < n; i++) {
            String token = tokens[i];
            switch (token) {
                case "+":
                    index--;
                    stack[index] += stack[index + 1];
                    break;
                case "-":
                    index--;
                    stack[index] -= stack[index + 1];
                    break;
                case "*":
                    index--;
                    stack[index] *= stack[index + 1];
                    break;
                case "/":
                    index--;
                    stack[index] /= stack[index + 1];
                    break;
                default:
                    index++;
                    stack[index] = Integer.parseInt(token);
            }
        }
        return stack[index];
    }
}

