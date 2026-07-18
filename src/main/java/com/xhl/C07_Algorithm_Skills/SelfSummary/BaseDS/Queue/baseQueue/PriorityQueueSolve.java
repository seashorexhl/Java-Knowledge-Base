package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Queue.baseQueue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: xhl
 * @Date: 2026-06-15 16:20
 * @Description: 优先级队列
 */
public class PriorityQueueSolve {
   static void main() {
      /**
       *  基础用法与大小顶堆
       * */
      // 1. 小顶堆（默认）：每次 poll() 出队的是最小值
      PriorityQueue<Integer> minHeap = new PriorityQueue<>();

      // 2. 大顶堆：每次 poll() 出队的是最大值
      // 写法一：使用 lambda 表达式
      PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
      // 写法二：使用内置方法（推荐，防止整数溢出）
      PriorityQueue<Integer> maxHeap2 = new PriorityQueue<>(Comparator.reverseOrder());
   }
   /**
    *  寻找数组中第 K 大的元素 (Top K)
    * */
   public int findKthLargest(int[] nums, int k) {
      // 维护一个大小为 k 的小顶堆
      PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);

      for (int num : nums) {
         if (minHeap.size() < k) {
            minHeap.offer(num);
         } else if (num > minHeap.peek()) {
            minHeap.poll();      // 弹出堆顶（当前第K大）
            minHeap.offer(num);  // 放入更大的元素
         }
      }
      return minHeap.peek(); // 堆顶即为第 K 大
   }

}
