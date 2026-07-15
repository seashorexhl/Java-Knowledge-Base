package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Stack.middle;

import java.util.Stack;

/**
 * @Author: xhl
 * @Date: 2026-06-27 16:34
 * @Description: Q2. 逆波兰表达式求值 150. 逆波兰表达式求值
 */
public class evalRPN {
    static void main() {
        String[] tokens = {"2","1","+","3","*"};
        evalRPN s = new evalRPN();
        int i = s.evalRPN(tokens);
        System.out.println("逆波兰表达式求值 i = " + i);
    }
    // 方法一：栈
    public int evalRPN(String[] tokens) {
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
    //方法二：数组模拟栈
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
