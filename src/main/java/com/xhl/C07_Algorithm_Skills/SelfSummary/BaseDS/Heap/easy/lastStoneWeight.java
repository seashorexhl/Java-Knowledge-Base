package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Heap.easy;

import java.util.PriorityQueue;

/**
 * @Author: xhl
 * @Date: 2026-07-09 23:41
 * @Description: 1046   最后一块石头的重量
 */
public class lastStoneWeight {
    static void main(String[] args) {
        int[] stones = {2,7,4,1,8,1};
        lastStoneWeight lsw = new lastStoneWeight();
        int lasted = lsw.lastStoneWeight(stones);
        System.out.println("最后一块石头的重量:"+ lasted);
    }
    // 方法一：最大堆
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a);
        for (int stone : stones) {
            pq.offer(stone);
        }

        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            if (a > b) {
                pq.offer(a - b);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }
}
