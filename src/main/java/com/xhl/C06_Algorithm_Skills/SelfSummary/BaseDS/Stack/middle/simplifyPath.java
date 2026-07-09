package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Stack.middle;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author: xhl
 * @Date: 2026-07-02 13:13
 * @Description: 71. 简化路径
 */
public class simplifyPath {
    static void main() {
        String Path = "/home/";
        simplifyPath sp = new simplifyPath();
        String s = sp.simplifyPath(Path);
        System.out.println("Simplify Path: 简化后的路径为：" + s);
    }
    //  方法一：栈

    public String simplifyPath(String path) {
        String[] names = path.split("/");
        Deque<String> stack = new ArrayDeque<String>();
        for (String name : names) {
            if ("..".equals(name)) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else if (name.length() > 0 && !".".equals(name)) {
                stack.offerLast(name);
            }
        }
        StringBuffer ans = new StringBuffer();
        if (stack.isEmpty()) {
            ans.append('/');
        } else {
            while (!stack.isEmpty()) {
                ans.append('/');
                ans.append(stack.pollFirst());
            }
        }
        return ans.toString();

    }
}
