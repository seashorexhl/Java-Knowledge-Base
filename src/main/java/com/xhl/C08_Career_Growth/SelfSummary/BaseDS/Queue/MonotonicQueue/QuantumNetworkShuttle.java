package com.xhl.Career_Growth.SelfSummary.BaseDS.Queue.MonotonicQueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-26 16:20
 * @Description: 量子网络穿梭 QuantumNetworkShuttle
 *  单调队列 滑动窗口最大值/最小值 + 动态规划
 */
public class QuantumNetworkShuttle {
    static void main() {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int k = in.nextInt();
            int n = in.nextInt();
            Long[] E = new Long[n];
            for(int i =0;i<n;i++){
                E[i] = in.nextLong();
            }
            if(n==0){
                System.out.println(0);
                return;
            }

            long[] dp = new long[n];
            Deque<Integer> q = new LinkedList<>();

            dp[0] = E[0];
            q.addLast(0);

            for(int i = 1;i<n;i++){
                if(!q.isEmpty() && q.peekFirst() < i - k){
                    q.removeFirst();
                }
                dp[i] = E[i] + dp[q.peekFirst()];

                while(!q.isEmpty() && dp[q.peekLast()] <= dp[i]){
                    q.removeLast();
                }
                q.addLast(i);
            }
            System.out.println(dp[n-1]);
        }
    }
}
