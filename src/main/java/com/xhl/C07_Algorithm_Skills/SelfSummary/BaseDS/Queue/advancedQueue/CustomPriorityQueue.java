package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Queue.advancedQueue;

/**
 * @Author: xhl
 * @Date: 2026-06-15 16:26
 * @Description: 3. Java 自定义实现模板（大根堆）
 * 利用一维数组来映射完全 二 叉 树，并精准实现元素的上浮（Swim）和下沉（Sink）操作
 */
import java.util.Arrays;

public class CustomPriorityQueue {
    private int[] heap;
    private int size;

    public CustomPriorityQueue(int capacity) {
        // 索引从1开始，因此数组长度需要 +1
        this.heap = new int[capacity + 1];
        this.size = 0;
    }

    // 插入元素：追加到末尾并上浮
    public void insert(int value) {
        if (size == heap.length - 1) {
            resize(heap.length * 2); // 扩容逻辑
        }
        heap[++size] = value;
        swim(size);
    }

    // 取出最大值：取堆顶，将末尾元素移至堆顶并下沉
    public int delMax() {
        if (size == 0) throw new RuntimeException("Queue is empty");
        int max = heap[1];
        heap[1] = heap[size--];
        sink(1);
        return max;
    }

    // 上浮操作
    private void swim(int k) {
        while (k > 1 && heap[k] > heap[k / 2]) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    // 下沉操作（注意必须先判断左右子节点是否存在）
    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k; // 左子节点
            // 如果右子节点存在且比左子节点大，则指向右子节点
            if (j < size && heap[j] < heap[j + 1]) j++;
            // 如果当前节点已经大于等于较大的子节点，则停止下沉
            if (heap[k] >= heap[j]) break;
            swap(k, j);
            k = j;
        }
    }

    // 辅助方法：交换元素
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // 辅助方法：数组扩容
    private void resize(int newCapacity) {
        heap = Arrays.copyOf(heap, newCapacity);
    }
}


