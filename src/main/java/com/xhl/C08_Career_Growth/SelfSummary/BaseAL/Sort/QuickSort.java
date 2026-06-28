package com.xhl.Career_Growth.SelfSummary.BaseAL.Sort;

import java.util.Arrays;

/**
 * @Author: xhl
 * @Date: 2026-06-08 05:25
 * @Description: 4.排序-快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[]  arr = {67,92,53,56,29};
        QuickSort s = new QuickSort();
        s.quickSort(arr);
        System.out.println(Arrays.toString(arr));

    }
    // 快速排序
    // 主入口方法
    public void quickSort(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        sort(nums, 0, nums.length - 1);
    }

    private void sort(int[] nums, int low, int high) {
        while (low < high) {
            // 【亮点】：小规模数据直接切到插入排序，减少递归开销
            if (high - low + 1 <= 10) {
                insertionSort(nums, low, high);
                break;
            }

            // 获取分区点
            int pivotIndex = partition(nums, low, high);

            // 【亮点】：优先对较短的子区间进行递归，较长区间用循环更新边界，防止极端情况下的栈溢出
            if (pivotIndex - low < high - pivotIndex) {
                sort(nums, low, pivotIndex - 1);
                low = pivotIndex + 1;
            } else {
                sort(nums, pivotIndex + 1, high);
                high = pivotIndex - 1;
            }
        }
    }

    // 核心分区逻辑 (Lomuto方案)
    private int partition(int[] nums, int low, int high) {
        int pivot = nums[high]; // 选最后一个元素作为基准
        int i = low - 1;        // i 指向小于等于 pivot 区域的右边界

        for (int j = low; j < high; j++) {
            if (nums[j] <= pivot) {
                swap(nums, ++i, j);
            }
        }
        swap(nums, i + 1, high); // 将基准归位
        return i + 1;            // 返回基准的最终索引
    }

    // 局部插入排序辅助
    private void insertionSort(int[] nums, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int temp = nums[i], j = i - 1;
            while (j >= low && nums[j] > temp) {
                nums[j + 1] = nums[j--];
            }
            nums[j + 1] = temp;
        }
    }

    // 交换辅助方法
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
