package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Stack.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-27 16:33
 * @Description: Q1. 用栈操作构建数组
 *
 */
public class buildArray {
    static void main() {
        int[] target = {1,2};
        int n = 4;
        buildArray ba = new buildArray();
        System.out.println(ba.buildArray(target,n));
    }
    // 方法一：模拟
    public List<String> buildArray(int[] target, int n) {
        List<String> res = new ArrayList<String>();
        int prev = 0;
        for (int number : target) {
            for (int i = 0; i < number - prev - 1; i++) {
                res.add("Push");
                res.add("Pop");
            }
            res.add("Push");
            prev = number;
        }
        return res;
    }
}
