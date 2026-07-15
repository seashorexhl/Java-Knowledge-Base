package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Heap.hard;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author: xhl
 * @Date: 2026-07-05 06:47
 * @Description:    502 :IPO
 */
public class findMaximizedCapital {
    public static void main(String[] args) {
        int k = 2;
        int w = 0;
        int[] profit = {1,2,3};
        int[] capital = {0,1,1};

        findMaximizedCapital fmc = new findMaximizedCapital();
        int maximizedCapital = fmc.findMaximizedCapital(k, w, capital, profit);
        System.out.println(maximizedCapital);
    }
    // 方法一：利用堆的贪心算法
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int curr = 0;
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; ++i) {
            arr[i][0] = capital[i];
            arr[i][1] = profits[i];
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
        for (int i = 0; i < k; ++i) {
            while (curr < n && arr[curr][0] <= w) {
                pq.add(arr[curr][1]);
                curr++;
            }
            if (!pq.isEmpty()) {
                w += pq.poll();
            } else {
                break;
            }
        }
        return w;
    }

}
