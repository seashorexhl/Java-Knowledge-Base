package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.May;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @Author: xhl
 * @Date: 2026-07-10 02:53
 * @Description:    数据包优先级窗口查找-100分 ⭐⭐⭐⭐⭐
 *  单调栈 + 滑动窗口 + 字符串解析
 * 题目描述：给定n个数据包，每个数据包包含id和priority。维护一个大小为k的滑动窗口，
 * 对于每个窗口，找出窗口内每个数据包右边第一个 priority 更高的数据包 id。
 */
public class solve {
    static void main(String[] args) {
        int n = 5; // 数据包数量
        int k = 3; // 窗口大小
        List<String> packets =  new ArrayList<>();// 数据包内容，长度为n的数组
        packets.add("1:5");
        packets.add("2:3");
        packets.add("3:7");
        packets.add("4:6");
        packets.add("5:4");
        solve sol= new solve();
        List<List<Integer>> res = sol.solve(n, k, packets);
        System.out.println(res.toString());
    }
    /**
     * 单调栈
     * */
    public List<List<Integer>> solve(int n, int k, List<String> packets) {
        List<List<Integer>> result = new ArrayList<>();
        if(k>n){
            return result;
        }
        int[] ids = new int[n];
        int[] priorities = new int[n];
        for(int i=0;i<packets.size();i++){
            String[] parts = packets.get(i).split(":");
            ids[i] = Integer.parseInt(parts[0]);
            priorities[i] = Integer.parseInt(parts[1]);
        }
        //  单调栈
        for (int start = 0; start <= n - k; start++) {
            int end = start + k - 1;
            int[] nextGreater = new int[k];
            Arrays.fill(nextGreater, -1);
            Stack<Integer> stack = new Stack<>();

            for (int j = end; j >= start; j--) {
                int curr_pri = priorities[j];
                while (!stack.isEmpty() && priorities[stack.peek()] <= curr_pri) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    nextGreater[j - start] = ids[stack.peek()];
                }
                stack.push(j);
            }

            List<Integer> windowResult = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                if (nextGreater[i] != -1) {
                    windowResult.add(nextGreater[i]);
                }
            }

            if (!windowResult.isEmpty()) {
                result.add(windowResult);
            }
        }
        return result;
    }
}
