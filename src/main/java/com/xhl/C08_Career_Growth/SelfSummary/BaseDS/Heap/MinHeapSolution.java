package com.xhl.Career_Growth.SelfSummary.BaseDS.Heap;

import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/**
 * @Author: xhl
 * @Date: 2026-06-15 19:00
 * @Description: 小顶堆（Min-Heap） 为例，提供可直接复用的手写堆模板。大顶堆只需反转比较逻辑。
 */
/**
 *  数据结构中的堆（Heap） 是一种基于完全 二 叉 树的优先级队列实现，核心通过数组存储和上浮/下沉操作
 *维护堆序性（父节点值 ≤ 子节点值为小顶堆，反之为大顶堆）。其关键优势在于 O(log n) 时间复杂度完成
 *插入和删除操作，适用于动态优先级管理场景（如任务调度、TopK问题）。以下从存储结构、核心方法模板到
 *典型应用展开说明。
 * 适用场景：动态优先级管理（如任务调度、实时消息分发）、TopK/中位数问题、贪心算法的支撑结构。
 * */
public  class  MinHeapSolution{
    static void main() {
        // 小顶堆（默认）
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // 大顶堆（自定义比较器）
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

    }
    // （1）TopK问题（小顶堆维护K个最大元素）
    public int[] topK(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            if (minHeap.size() < k) {
                minHeap.offer(num);
            } else if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(num);
            }
        }
        return minHeap.stream().mapToInt(i -> i).toArray();
    }
    /**1.初始化 */
    //堆的本质：数组实现的完全 二叉树
    public class MinHeap {

        private int[] heap;
        private int size;

        public MinHeap(int capacity) {
            heap = new int[capacity];
            size = 0;
        }

        // 辅助方法：交换元素
        private void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        // 获取父节点索引
        private int parent(int i) {
            return (i - 1) / 2;
        }

        // 获取左子节点索引
        private int leftChild(int i) {
            return 2 * i + 1;
        }

        // 获取右子节点索引
        private int rightChild(int i) {
            return 2 * i + 2;
        }

        /** 2.核心操作实现*/
        //（1）插入元素（insert）
        public void insert(int value) {
            if (size == heap.length) throw new IllegalStateException("Heap is full");
            heap[size] = value;
            siftUp(size); // 从末尾开始上浮
            size++;
        }
        // 上浮（siftUp）
        private void siftUp(int index) {
            while (index > 0 && heap[index] < heap[parent(index)]) {
                swap(index, parent(index));
                index = parent(index);
            }
        }
        /*（2）删除堆顶元素（poll）*/
        public int poll() {
            if (size == 0) throw new NoSuchElementException("Heap is empty");

            // 1. 保存堆顶元素（最小值），用于最后返回
            int min = heap[0];

            // 2. 【修正点】将末尾元素移动到堆顶 (索引0)
            // 原代码: heap = heap[size - 1]; (错误)
            heap[0] = heap[size - 1];

            // 3. 减少堆的大小
            size--;

            // 4. 从堆顶开始下沉，恢复堆性质
            siftDown(0);

            return min;
        }

        /*下沉（siftDown）*/
        private void siftDown(int index) {
            int smallest = index;
            int left = leftChild(index);
            int right = rightChild(index);

            // 找出左右子节点中更小者
            if (left < size && heap[left] < heap[smallest]) smallest = left;
            if (right < size && heap[right] < heap[smallest]) smallest = right;

            // 若需调整，交换后递归下沉
            if (smallest != index) {
                swap(index, smallest);
                siftDown(smallest);
            }
        }
    }

}
