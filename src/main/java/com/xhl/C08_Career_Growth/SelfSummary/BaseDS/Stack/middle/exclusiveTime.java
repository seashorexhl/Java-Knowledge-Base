package com.xhl.Career_Growth.SelfSummary.BaseDS.Stack.middle;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-27 16:40
 * @Description: Q3. 函数的独占时间
 */
public class exclusiveTime {
    static void main() {
        int n = 2;
        List<String> logs = Arrays.asList("0:start:0","1:start:2","1:end:5","0:end:6");
        exclusiveTime ex = new exclusiveTime();
        System.out.println(Arrays.toString(ex.exclusiveTime(n,logs)));
    }
    //    方法一：栈
    public int[] exclusiveTime(int n, List<String> logs) {
        Deque<int[]> stack = new ArrayDeque<int[]>(); // {idx, 开始运行的时间}
        int[] res = new int[n];
        for (String log : logs) {
            int idx = Integer.parseInt(log.substring(0, log.indexOf(':')));
            String type = log.substring(log.indexOf(':') + 1, log.lastIndexOf(':'));
            int timestamp = Integer.parseInt(log.substring(log.lastIndexOf(':') + 1));
            if ("start".equals(type)) {
                if (!stack.isEmpty()) {
                    res[stack.peek()[0]] += timestamp - stack.peek()[1];
                    stack.peek()[1] = timestamp;
                }
                stack.push(new int[]{idx, timestamp});
            } else {
                int[] t = stack.pop();
                res[t[0]] += timestamp - t[1] + 1;
                if (!stack.isEmpty()) {
                    stack.peek()[1] = timestamp + 1;
                }
            }
        }
        return res;
    }

}
