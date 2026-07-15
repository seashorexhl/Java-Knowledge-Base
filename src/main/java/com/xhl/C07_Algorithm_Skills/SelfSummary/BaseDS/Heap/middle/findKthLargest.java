package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Heap.middle;

/**
 * @Author: xhl
 * @Date: 2026-07-05 06:46
 * @Description:    215. 数组中的第K个最大元素
 */
public class findKthLargest {
    static void main() {
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        findKthLargest fk = new findKthLargest();
        System.out.println("--------基于快速排序的选择方法--------");
        int kthLargest = fk.findKthLargest(nums, k);

        System.out.println("数组中的第K个最大元素:"+kthLargest);
        System.out.println("--------基于堆排序的选择方法---------");
        int kthLargest1 = fk.findKthLargest1(nums, k);
        System.out.println("数组中的第K个最大元素:"+kthLargest1);
    }
    // 方法一：基于快速排序的选择方法
    int quickselect(int[] nums, int l, int r, int k) {
        if (l == r) return nums[k];
        int x = nums[l], i = l - 1, j = r + 1;
        while (i < j) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j){
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
        if (k <= j) return quickselect(nums, l, j, k);
        else return quickselect(nums, j + 1, r, k);
    }
    public int findKthLargest(int[] _nums, int k) {
        int n = _nums.length;
        return quickselect(_nums, 0, n - 1, n - k);
    }

    // 方法二：基于堆排序的选择方法
    public int findKthLargest1(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            swap(nums, 0, i);
            --heapSize;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    public void buildMaxHeap(int[] a, int heapSize) {
        for (int i = heapSize / 2 - 1; i >= 0; --i) {
            maxHeapify(a, i, heapSize);
        }
    }

    public void maxHeapify(int[] a, int i, int heapSize) {
        int l = i * 2 + 1, r = i * 2 + 2, largest = i;
        if (l < heapSize && a[l] > a[largest]) {
            largest = l;
        }
        if (r < heapSize && a[r] > a[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(a, i, largest);
            maxHeapify(a, largest, heapSize);
        }
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
