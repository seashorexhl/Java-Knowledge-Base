package com.xhl.Career_Growth.SelfSummary.BaseAL.Sort;

/**
 * @Author: xhl
 * @Date: 2026-06-09 00:16
 * @Description:
 */
public class HeapSort {
    static void main() {

    }
    public static void heapSort(int[] arr) {
        int n = arr.length;
        // 1. 建大顶堆 (从最后一个非叶子节点开始调整)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        // 2. 依次将堆顶(最大值)与末尾交换，并重新调整
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0]; arr[0] = arr[i]; arr[i] = temp;
            heapify(arr, i, 0); // 注意：这里堆的大小变成了 i
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) largest = left;
        if (right < n && arr[right] > arr[largest]) largest = right;

        if (largest != i) {
            int temp = arr[i]; arr[i] = arr[largest]; arr[largest] = temp;
            heapify(arr, n, largest); // 递归调整受影响的子树
        }
    }
}
